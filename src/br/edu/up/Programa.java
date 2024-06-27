package br.edu.up;

public class Programa {
    public static void main(String[] args) {
        try {
            AlunoPrograma.main(args);
            System.out.println("\n\n\nExecução do programa concluída com sucesso.\n\n\n");
        } catch (Exception e) {
            System.err.println("\nErro durante a execução do programa: \n" + e.getMessage());
            e.printStackTrace();
        }
    }
}
