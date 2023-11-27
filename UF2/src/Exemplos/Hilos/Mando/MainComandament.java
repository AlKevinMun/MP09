package Exemplos.Hilos.Mando;

public class MainComandament {
    public static void main(String[] args) {
        Mando com = new Mando();
        MiembrosFamilia padre = new MiembrosFamilia("Pepe",com);
        MiembrosFamilia madre = new MiembrosFamilia("Antonia",com);
        MiembrosFamilia hijo = new MiembrosFamilia("Jose",com);
        MiembrosFamilia perro = new MiembrosFamilia("Roc",com);
        padre.start();
        hijo.start();
        madre.start();
        perro.start();
    }
}
