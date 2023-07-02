package Proj;

public class StateDevedor implements State{

    private Usuarios usuario;
    private String nome = "Devedor";

    public StateDevedor(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void changeState() {
        usuario.setEstado(new StateNormal(usuario));
    }

    public String getNome() {
        return nome;
    }
}
