package Examen.Bateria;


public class Menu {
    Bateria bateria;
    CarrgarBateria carrgarBateria = new CarrgarBateria(bateria);
    DescarregarBateria descarregarBateria = new DescarregarBateria(bateria);

    public Menu(Bateria bateria) {
        this.bateria = bateria;
        this.carrgarBateria = new CarrgarBateria(bateria);
        this.descarregarBateria = new DescarregarBateria(bateria);
    }

    public void mostrarMenu() {
        System.out.println("1 - Carregar bateria");
        System.out.println("2 - Jugar");
        System.out.println("3 - Info Bateria");
        System.out.println("0 - Sortir");
    }
    public void escogerOpcion(int opcion) {

        switch (opcion){
            case 1:
                if (bateria.getNivell()==100){
                    System.out.println("La bateria ya esta cargada");
                }
                else carrgarBateria.start();
                break;
            case 2:
                if (bateria.getNivell()==100){
                descarregarBateria.start();
                }
                else System.out.println("La bateria no se encuentra cargada");
                break;
            case 3:
                System.out.println("Actualmente la bateria tiene el siguiente porcentaje: "+bateria.getNivell()+"%");
                break;
            case 0:
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }
}
