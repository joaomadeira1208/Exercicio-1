package local.segundoprojeto;

public class Funcionario {
    private int codigo;
    private String nome;
    private String sobrenome;
    private double salario;

    public Funcionario() {
        this.codigo = -1;
        this.nome = "";
        this.sobrenome = "";
        this.salario = 0.0;
    }

    public Funcionario(int codigo, String nome, String sobrenome, double salario) {
        this.codigo = codigo;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.salario = salario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario [codigo=" + codigo + ", nome=" + nome + ", sobrenome=" + sobrenome + ", salario=" + salario + "]";
    }
}
