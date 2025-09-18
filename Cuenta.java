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

    
    public Cuenta(double pSaldo){
        this("", pSaldo);
    }   
     
    public Cuenta(String nombreCuentaHabiente, double pSaldo)
    {
        this.nombreCuentaHabiente = nombreCuentaHabiente;
        saldo = pSaldo;
        
        fechaCreacion = determinarFechaHoraActual();
        
        cantDepositosRealizados = 0;
        cantRetirosExitososRealizados = 0;
        
        cantCuentasCreadas ++;
        codCuenta += cantCuentasCreadas;
    }
    
    public void setNombreCuentaHabiente(String pNombreCuentaHabiente){
        nombreCuentaHabiente = pNombreCuentaHabiente;
        System.out.println("Nombre de la cuenta habiente" + "(" + nombreCuentaHabiente + ")" + " establecido");
    }
    
    public String getCodCuenta(){
        return codCuenta;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public double depositar(double monto){
        if (monto <= 0) {
            System.out.println("El monto a depositar debe ser mayor que cero.");
            return saldo;
        }
        saldo += monto;
        cantDepositosRealizados ++;
        return saldo;
    }
    
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
    
    private boolean validarRetiro(double monto){
        return saldo >= monto;
    }
    
    public static int getCantCuentasCreadas(){
        return cantCuentasCreadas;
    }
    
    private String determinarFechaHoraActual(){
        Date fecha = new Date(System.currentTimeMillis());
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return formatoFecha.format(fecha);
    }
    
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