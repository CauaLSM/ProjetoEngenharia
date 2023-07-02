package Proj;

public class ComportamentoNormal implements Comportamento {

    private Usuarios usuario;
    private final String nome = "Normal";

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

