package local.meuprojeto;
import local.meuprojeto.MyIO;

public class SomarDoisNumeros {
	public static void main(String[] args) {
		//Declaração de variaveis e leitura dos números
		int num1 = MyIO.readInt("Digite um numero: ");
		int num2 = MyIO.readInt("Digite outro numero: ");
		
		//Somar
		int soma = num1 + num2;
		
		//Escrever na tela
		MyIO.println("Soma: " + soma);
	}
}
