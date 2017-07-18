import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

	public static void main(String[] args) {
		Scanner scannerString = new Scanner(System.in);
		Scanner scannerInt = new Scanner(System.in);
		int quantUsuario, quantRecurso;
		System.out.println("Digite a quantidade de usuarios:\n");
		quantUsuario = scannerInt.nextInt();
		System.out.println("Digite a quantidade de recursos:\n");
		quantRecurso = scannerInt.nextInt();
		int opc = 0;
		int idUsuario = 0;
		int idBuscaUsuario;
		String[] nomeUsuario = new String[quantUsuario];
		String[] emailUsuario = new String[quantUsuario];
		int[] funcaoUsuario = new int[quantUsuario];
		// 0 = Aluno, 1 = Professor, 2 = Pesquisador, 3 = ADM

		int idRecurso = 0;
		int idBuscaRecurso;
		int[] identRecurso = new int[quantRecurso];
		// 0 = Laboratorio, 1 = Auditorio, 2 = Sala de Aula, 3 = Projetor
		String[] statusRecurso = new String[quantRecurso];
		int[] usuarioAssociado = new int[quantRecurso];
		GregorianCalendar[][] dataInicio = new GregorianCalendar[quantRecurso][100];
		GregorianCalendar[][] dataTermino = new GregorianCalendar[quantRecurso][100];
		int idLocacao = 0;
		int[] tipoAtividade = new int[quantRecurso];
		// 1 = Aula Tradicional, 2 = Apresentacao, 3 = Laboratorio
		String[][] tituloAtividade = new String[quantRecurso][100];
		String[][] descrAtividade = new String[quantRecurso][100];
		String[][] materialApoio = new String[quantRecurso][100];
		int[][][] idParticipantes = new int[quantRecurso][100][quantUsuario];

		do {
			System.out.println(
					"Menu:\n1 - Cadastrar Usuario\n2 - Cadastrar Recurso\n3 - Marcar Alocacao de Recurso\n4 - Confirmar Alocacao\n5 - Concluir Alocacao\n6 - Consultar Usuario\n 7 - Consultar Recurso\n0 - Sair\nEscolha uma opcao: ");
			opc = scannerInt.nextInt();
			switch (opc) {
			case 1:
				System.out.println("Digite o nome do usuario:\n");
				nomeUsuario[idUsuario] = scannerString.nextLine();
				System.out.println("Digite o e-mail do usuario:\n");
				emailUsuario[idUsuario] = scannerString.nextLine();
				System.out.println("Digite a funcao(0 = Aluno, 1 = Professor, 2 = Pesquisador, 3 = ADM) do usuario:\n");
				funcaoUsuario[idUsuario] = scannerInt.nextInt();
				idUsuario++;
				break;
			case 2:
				statusRecurso[idRecurso] = "Em processo de alocacao";
				System.out.println(
						"Digite a identificacao(0 = Laboratorio, 1 = Auditorio, 2 = Sala de Aula, 3 = Projetor) do recurso:\n");
				identRecurso[idRecurso] = scannerInt.nextInt();
				idRecurso++;
				break;
			case 3:
				int ano, mes, dia, hora, minuto;
				System.out.println("Digite o ID do recurso a ser alocado:\n");
				idBuscaRecurso = scannerInt.nextInt();
				if (statusRecurso[idBuscaRecurso].equals("Em processo de alocacao")) {
					System.out.println("Digite o ID do usuario que deseja alocar o recurso:\n");
					idBuscaUsuario = scannerInt.nextInt();
					if (idBuscaUsuario != 0) {
						usuarioAssociado[idBuscaRecurso] = idBuscaUsuario;
						System.out.println("Digite o ano de inicio da alocacao:\n");
						ano = scannerInt.nextInt();
						System.out.println("Digite o mes de inicio da alocacao:\n");
						mes = scannerInt.nextInt();
						System.out.println("Digite o dia de inicio da alocacao:\n");
						dia = scannerInt.nextInt();
						System.out.println("Digite as horas de inicio da alocacao:\n");
						hora = scannerInt.nextInt();
						System.out.println("Digite os minutos de inicio da alocacao:\n");
						minuto = scannerInt.nextInt();
						dataInicio[idBuscaRecurso][idLocacao] = new GregorianCalendar(ano, mes, dia, hora, minuto);
						System.out.println("Digite o ano de termino da alocacao:\n");
						ano = scannerInt.nextInt();
						System.out.println("Digite o mes de termino da alocacao:\n");
						mes = scannerInt.nextInt();
						System.out.println("Digite o dia de termino da alocacao:\n");
						dia = scannerInt.nextInt();
						System.out.println("Digite as horas de termino da alocacao:\n");
						hora = scannerInt.nextInt();
						System.out.println("Digite os minutos de termino da alocacao:\n");
						minuto = scannerInt.nextInt();
						dataTermino[idBuscaRecurso][idLocacao] = new GregorianCalendar(ano, mes, dia, hora, minuto);
						idLocacao++;
						statusRecurso[idBuscaRecurso] = "Alocado";
						System.out.println("");
					} else {
						System.out.println("Alunos nao podem alocar recursos\n");
					}
				} else {
					System.out.println("O recurso nao pode ser alocado\n");
				}
				break;
			case 4:
				System.out.println("Digite o ID do recurso para confirmar alocacao:\n");
				idBuscaRecurso = scannerInt.nextInt();
				if (statusRecurso[idBuscaRecurso].equals("Alocado")) {
					statusRecurso[idBuscaRecurso] = "Em andamento";
				} else {
					System.out.println("A alocacao do recurso nao pode se confirmada");
				}
				break;
			case 5:
				System.out.println("Digite o ID do recurso para concluir alocacao:\n");
				idBuscaRecurso = scannerInt.nextInt();
				if (statusRecurso[idBuscaRecurso].equals("Em andamento")) {
					System.out.println(
							"Digite a atividade(1 = Aula Tradicional, 2 = Apresentacao, 3 = Laboratorio) que sera realizada:\n");
					tipoAtividade[idBuscaRecurso] = scannerInt.nextInt();
					if (((tipoAtividade[idBuscaRecurso] == 1) || (tipoAtividade[idBuscaRecurso] == 3))
							&& usuarioAssociado[idBuscaRecurso] != 1) {
						System.out.println("Apenas professores podem realizar esta atividade:\n");
					} else {
						System.out.println("Digite o titulo da atividade que sera realizada:\n");
						tituloAtividade[idBuscaRecurso] = scannerString.nextLine();
						System.out.println("Digite a descricao da atividade que sera realizada:\n");
						descrAtividade[idBuscaRecurso] = scannerString.nextLine();
						System.out
								.println("Digite os materias que serao necessarios para a atividade ser realizada:\n");
						materialApoio[idBuscaRecurso] = scannerString.nextLine();
						int i = 0;
						do {
							System.out.println(
									"Digite o ID do " + (i + 1) + " usuario associado a atividade(-1 para parar):\n");
							idParticipantes[idBuscaRecurso][i] = scannerInt.nextInt();
							i++;
						} while (idParticipantes[idBuscaRecurso][i - 1] != -1);
						statusRecurso[idBuscaRecurso] = "Concluido";
					}
				} else {
					System.out.println("A alocacao do recurso nao pode ser concluida\n");
				}
				break;
			case 6:
				System.out.println("Digite o ID do usuario que deseja consultar:\n");
				idBuscaUsuario = scannerInt.nextInt();
				if (idBuscaUsuario < idUsuario) {
					System.out.println("Nome: " + nomeUsuario[idBuscaUsuario] + "\nE-mail: "
							+ emailUsuario[idBuscaUsuario] + "\nHistorico:" + "\n\tRecursos Alocados:");
					for (int i = 0; i < idRecurso; i++) {
						if (usuarioAssociado[i] == idBuscaUsuario) {
							System.out.println("\n\tRecurso: ");
							switch (identRecurso[i]) {
							case 0:
								System.out.println("Laboratorio ");
								break;
							case 1:
								System.out.println("Auditorio ");
								break;
							case 2:
								System.out.println("Sala De Aula ");
								break;
							case 3:
								System.out.println("Projetor ");
								break;
							}
							System.out.println("ID: " + i);
						}
					}
				} else {
					System.out.println("Usuario nao pode ser encontrado\n");
				}
				break;
			case 7:
				System.out.println("Digite o ID do recurso que deseja consultar:\n");
				idBuscaRecurso = scannerInt.nextInt();
				if (idBuscaRecurso < idRecurso) {
					System.out.println("Associado: " + "\nNome: " + nomeUsuario[usuarioAssociado[idBuscaRecurso]]
							+ "\nE-mail: " + emailUsuario[usuarioAssociado[idBuscaRecurso]] + "\nID:"
							+ usuarioAssociado[idBuscaRecurso]);
				} else {
					System.out.println("Recurso nao pode ser encontrado\n");
				}
				break;
			}
		} while (opc != 0);

	}
}