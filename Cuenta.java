import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Cuenta
{
    private String codCuenta = "cta-";
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    private static int cantCuentasCreadas = 0;

    //constructor con un solo parametro
    public Cuenta(double pSaldo){
        this("", pSaldo);
    }   
     
    //constructos con dos parametros
    public Cuenta(String nombreCuentaHabiente, double pSaldo)
    {
        this.nombreCuentaHabiente = nombreCuentaHabiente;
        saldo = pSaldo;
        
        // Registrar fecha y hora de creacion
        fechaCreacion = determinarFechaHoraActual();
        
        cantDepositosRealizados = 0;
        cantRetirosExitososRealizados = 0;
        
        // Incrementar contador de cuentas y generar codigo único
        cantCuentasCreadas ++;
        codCuenta += cantCuentasCreadas;
    }
    
    //Establece o actualiza el nombre Cuenta Habiente
    public void setNombreCuentaHabiente(String pNombreCuentaHabiente){
        nombreCuentaHabiente = pNombreCuentaHabiente;
        System.out.println("Nombre de la cuenta habiente" + "(" + nombreCuentaHabiente + ")" + " establecido");
    }
    
    //Obtiene el codigo unico de la cuenta
    public String getCodCuenta(){
        return codCuenta;
    }
    
    //Obtiene el saldo actual de la cuenta
    public double getSaldo(){
        return saldo;
    }
    
    // Realiza un deposito en la cuenta
    public double depositar(double monto){
        if (monto <= 0) {
            System.out.println("El monto a depositar debe ser mayor que cero.");
            return saldo;
        }
        saldo += monto;
        cantDepositosRealizados ++;
        return saldo;
    }
    
    //Realiza un retiro de la cuenta solo si hay saldo suficiente
    public double retirar(double monto){
        if (monto <= 0) {
            System.out.println("El monto a retirar debe ser mayor a cero.");
            return saldo;
        }
        if (validarRetiro(monto)){
            saldo -= monto;
            cantRetirosExitososRealizados ++;
            return saldo;
        }
        System.out.println("El retiro no se puede efectuar por Saldo insuficiente");
        return saldo;
    }
    
    //Valida si es posible realizar un retiro
    private boolean validarRetiro(double monto){
        return saldo >= monto;
        }
     
        //Obtiene la cantidad de cuentas creadas
    public static int getCantCuentasCreadas(){
        return cantCuentasCreadas;
    }
    
    //Obtiene la cantidad de cuentas creadas
    private String determinarFechaHoraActual(){
        Date fecha = new Date(System.currentTimeMillis());
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return formatoFecha.format(fecha);
    }
    
    //Determina la fecha y hora actual
    public String toString() {
        String msg = "";
        msg += "Codigo Cuenta: " + codCuenta + "\n";
        msg += "Saldo: " + saldo + "\n";
        msg += "Nombre Habiente: " + nombreCuentaHabiente + "\n";
        msg += "Fecha de Creacion: " + fechaCreacion + "\n";
        msg += "Cantidad Depositos Realizados: " + cantDepositosRealizados + "\n";
        msg += "Cantidad Retiros Exitosos Realizados: " + cantRetirosExitososRealizados + "\n";
        return msg;
    }
}