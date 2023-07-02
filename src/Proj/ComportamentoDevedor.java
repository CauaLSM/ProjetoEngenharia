package Proj;

public class ComportamentoDevedor implements Comportamento {

    private Usuarios usuario;
    private String nome = "Devedor";

    public ComportamentoDevedor(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void mudarComportamento() {
        usuario.setEstado(new ComportamentoNormal(usuario));
    }

    public String getNome() {
        return nome;
    }
}
