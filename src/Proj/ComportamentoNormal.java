package Proj;

public class ComportamentoNormal implements Comportamento {

    private Usuarios usuario;
    private String nome = "Normal";

    public ComportamentoNormal(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public void mudarComportamento() {
        usuario.setComportamento(new ComportamentoDevedor(usuario));
    }

    @Override
    public String getNome() {
        return nome;
    }
}

