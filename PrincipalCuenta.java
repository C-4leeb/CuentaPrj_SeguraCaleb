import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class PrincipalCuenta{
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         List <Cuenta> cuentas = new ArrayList<>();
         int actual = -1; //Cuenta actualmente seleccionada
     
         System.out.println("\nMenú principal"); 
     
         boolean salir = false;
         
         // Ciclo principal del menu
         while(!salir) {
            System.out.println("1) Crear Cuenta"); 
            System.out.println("2) Conocer la cantidad de Cuentas Creadas"); 
            System.out.println("3) Listar Cuentas"); 
            System.out.println("4) Seleccionar Cuenta actual"); 
            System.out.println("5) Depositar"); 
            System.out.println("6) Retirar"); 
            System.out.println("7) Consultar Saldo"); 
            System.out.println("8) Consultar Estado (toString)"); 
            System.out.println("9) Establecer Nombre de Cuenta Habiente");
            System.out.println("0) Salir"); 
            String op = sc.nextLine().trim();
            
            switch (op) {
                case "1":{ // Crear nueva cuenta
                    System.out.println("Saldo de la cuenta");
                    String lineaSaldo = sc.nextLine().trim();
                    System.out.print("Nombre de la Cuenta Habiente");
                    String lineaNombre = sc.nextLine().trim();
                    Cuenta c;
                    sc.nextLine();
    
                    double saldo;
                    try{
                        saldo = Double.parseDouble(lineaSaldo);
                    }catch (NumberFormatException e){
                        System.out.println("Numero invalido, ingrese nuevamente el saldo");
                        break;
                    }
                        
                    if (lineaNombre.isEmpty()) 
                        c = new Cuenta(saldo);
                    else 
                        c = new Cuenta(lineaNombre, saldo);
                
                    cuentas.add(c);
                    actual = cuentas.size() - 1;
                    System.out.println("Cuenta Crea y seleccionado (índice " + actual + ")");
                    break;
                }
                case "2": { // Mostrar la cantidad de cuentas creadas
                    Cuenta c = cuentas.get(actual);
                    int cantidadCuentas = c.getCantCuentasCreadas();
                    System.out.println("Cantidad de cuentas creadas: " + cantidadCuentas);
                    break;
                }
                case "3": { // Listar todas las cuentas con su indice, codigo y saldo
                    if (cuentas.isEmpty())
                        System.out.println("No hay cuentas creadas.");
                    else{
                        System.out.println("Indice | Codigo Cuenta | Saldo");
                        for (int i = 0; i < cuentas.size(); i++){
                            Cuenta c = cuentas.get(i);
                            System.out.printf("  %d    |      %s   | %.2f%n",
                                i,
                                c.getCodCuenta(),
                                c.getSaldo());
                        }
                    }
                    break;
                }
                case "4": {      // Seleccionar la cuenta actual por indice
                    if (cuentas.isEmpty()){
                        System.out.println("Cree una cuenta primero");
                        break;
                    }
                    System.out.print("Índice de la cuenta a seleccionar: ");
                    String indice = sc.nextLine().trim();
                    try {
                            int indiceSeleccionado = Integer.parseInt(indice);
                            if (indiceSeleccionado >= 0 && indiceSeleccionado < cuentas.size()) {
                                actual = indiceSeleccionado;
                                System.out.println("Cuenta de índice " + actual + " seleccionado.");
                            } else {
                                System.out.println("Índice fuera de rango.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Índice inválido.");
                        }
                        break;
                }
                
                case "5": {  // Depositar en la cuenta 
                    if (actual < 0 || cuentas.isEmpty()) {
                            System.out.println("Debe crear y seleccionar una Cuenta primero.");
                            break;
                        }
                    Cuenta c = cuentas.get(actual);
                    System.out.println("Indique el monto a depositar en la cuenta con indice" + actual);
                    String entradaMonto = sc.nextLine().trim();
                    try {
                        double monto = Double.parseDouble(entradaMonto);    
                        System.out.printf("Saldo = %.2f%n  " , c.depositar(monto));
                        } catch (NumberFormatException e) {
                            System.out.println("Monto inválido.");
                        }
                    break;
                }
                
                case "6":{ // Retirar de la cuenta
                    if (actual < 0 || cuentas.isEmpty()) {
                            System.out.println("Debe crear y seleccionar una Cuenta primero.");
                            break;
                        }
                    Cuenta c = cuentas.get(actual);
                    System.out.println("Indique el monto a retirar en la cuenta con indice" + actual);
                    String entradaMonto = sc.nextLine().trim();
                    try {
                        double monto = Double.parseDouble(entradaMonto);    
                        System.out.printf("Saldo = %.2f%n  " , c.retirar(monto));
                        } catch (NumberFormatException e) {
                            System.out.println("Monto inválido.");
                        }
                    break;
                }
                
                case "7":{  // Consultar saldo de la cuenta
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.printf("Saldo de Cuenta: %.2f%n", cuentas.get(actual).getSaldo());
                        break;
                }
                
                case "8": { // Mostrar estado completo de la cuenta 
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.println(cuentas.get(actual).toString());
                    break;
                }
                
                case "9": { // Establecer o actualizar nombre cuenta habiente
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    Cuenta c = cuentas.get(actual);
                    System.out.println("Indique el nombre de la cuenta habiente a establecer a la cuenta con indice"+ actual);
                    String nombre = sc.nextLine().trim();
                    c.setNombreCuentaHabiente(nombre);
                    break;
                }
                
                case "0": { // Salir 
                    salir = true;
                    System.out.println("Saliendo...");
                    break;
                }
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}
