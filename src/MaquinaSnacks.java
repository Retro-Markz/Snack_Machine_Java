import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {

    public static void main(String[] args) {
        maquinaSnacks();
    }


    public static void maquinaSnacks(){
        var salir = false;
        var console = new Scanner(System.in);

        //crear la lista de productos de tipo snack

        List<Snack> productos = new ArrayList<>();
        System.out.println("*** Maquina de Snacks ***");

        Snacks.showSnacks(); //Mostrar inventario de snacks disponinbles
        while(!salir){
            try{
                var opcion = mostrarMenu(console);
                salir = ejecutarOpciones(opcion, console, productos);

            }
            catch (Exception e){
                System.out.println("Ocurrio un error " + e.getMessage());
            }
            finally {
                System.out.println(); //imprime un salto de linea con cada iteracion
            }
            }


    }


    private static int  mostrarMenu(Scanner console){
        System.out.print("""
                Menu: 
                1. Comprar Snack
                2: Mostrar ticket
                3. Agregar nuevo snack
                4 Salir
                
                Elige una opcion: \s""");

        //leemos y retornamos la opcion seleccionada por el usuario
        return Integer.parseInt(console.nextLine());
    }


    private static boolean  ejecutarOpciones(int opcion, Scanner console, List<Snack> prodcutos){
        var salir = false;
        switch (opcion){
            case 1 -> comprarSnack(console, prodcutos);
            case 2 -> mostrarTicket(prodcutos);
            case 3 -> agregarSnack(console);
            case 4 -> {
                System.out.println("Regresa pronto");
                salir = true;
            }
        }

        return salir;
    }


    private static void comprarSnack(Scanner console, List<Snack> productos){
        System.out.print("Que Snack quieres comprar? (selecciona id)");
        var idSnack = Integer.parseInt(console.nextLine());
        //validar que el snack exista
        var snackEncontrado = false;
        for(var snack: Snacks.getSnacks()){
            if(idSnack == snack.getIdSnack()){
                //agregamos el snack a la lista de productos
                productos.add(snack);
                System.out.println("Snack agregado a la lista: " + snack);
                snackEncontrado = true;
                break;

            }
        }
        if (!snackEncontrado){
            System.out.println("id de Snack no encontrado: " + idSnack);
        }
    }

    private static void mostrarTicket(List<Snack> productos){
        var ticket = "**** ticket de venta ****";
        var total = 0.0;
        for(var producto: productos){
            ticket += "\n\t-" + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();

        }
        ticket += "\n\tTotal -> $" + total;
        System.out.println(ticket);
    }

    private static void agregarSnack(Scanner console){
        System.out.println("Nombre del Snack que desea agregar: ");
        var nombre = console.nextLine();
        System.out.println("Precio del nuevo Snack: ");
        var precio = Double.parseDouble(console.nextLine());
        Snacks.addSnack(new Snack(nombre, precio));
        System.out.println("Tu Snack se ha agregado correctamente");
        Snacks.showSnacks();
    }
}
