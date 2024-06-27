import java.io.*;
import java.util.*;
import br.edu.up.*;

public class AlunoProcessor {
    public static void main(String[] args) {
        List<Aluno> listaDeAlunos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("alunos.csv"))) {
            String linha;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int matricula = Integer.parseInt(partes[0]);
                String nome = partes[1];
                double nota = Double.parseDouble(partes[2].replace(',', '.'));
                listaDeAlunos.add(new Aluno(matricula, nome, nota));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int totalAlunos = listaDeAlunos.size();
        int aprovados = 0;
        int reprovados = 0;
        double somaNotas = 0;
        double menorNota = Double.MAX_VALUE;
        double maiorNota = Double.MIN_VALUE;

        for (Aluno aluno : listaDeAlunos) {
            double nota = aluno.getNota();
            somaNotas += nota;
            if (nota >= 6.0) {
                aprovados++;
            } else {
                reprovados++;
            }
            if (nota < menorNota) {
                menorNota = nota;
            }
            if (nota > maiorNota) {
                maiorNota = nota;
            }
        }
        double mediaNotas = somaNotas / totalAlunos;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("resumo.csv"))) {
            bw.write("Quantidade de alunos;Aprovados;Reprovados;Menor nota;Maior nota;Média das notas\n");
            bw.write(totalAlunos + ";" + aprovados + ";" + reprovados + ";" +
                    String.format("%.1f", menorNota) + ";" +
                    String.format("%.1f", maiorNota) + ";" +
                    String.format("%.2f", mediaNotas) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
