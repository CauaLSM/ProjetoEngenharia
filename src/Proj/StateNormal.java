package Proj;

public class StateNormal implements State{

    private Usuarios usuario;
    private String nome = "Normal";

    public void changeState() {
        usuario.setEstado(new StateDevedor(usuario));
    }

    public String getNome() {
        return nome;
    }

    public StateNormal(Usuarios usuario) {
        this.usuario = usuario;
    }
}
