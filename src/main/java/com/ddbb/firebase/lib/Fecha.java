package com.ddbb.firebase.lib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Clase de manejo de fechas
 * @author Guillermo Casas Reche
 * @author g.casas.r94@gmail.com
 * @version 0.5
 */
public class Fecha {

    /*
     * ==========================================================================
     * ====================== VARIABLES =========================================
     * ==========================================================================
     */

    //=============================================================\
    private long fecha; // Variable principal. AlmacenarÃ¡ la fecha ||
    //=============================================================/

    private String formato = "yyyy-MM-dd";          // Formato por defecto
    private static final int MILIS_DAY = 86400000;  // Milisegundos que tiene un dia
    public static final String FORMATO_ALL = "dd-MM-yyyy HH:mm:ss"; // Formato de tiempo completo

    // Listas de nombres
    private static final String[] nomMeses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    private static final String[] nomSemana = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};


    /*
     * ==========================================================================
     * ====================== CONSTRUCTORES =====================================
     * ==========================================================================
     */
    /**
     * Obtenemos Fecha de ahora
     */
    public Fecha() {
        setTime();
    }

    /**
     * Obtenemos Fecha con la fecha indicada
     * @param dateFecha Fecha a establecer
     */
    public Fecha(Date dateFecha){
        setTime(dateFecha);
    }

    /**
     * Obtenemos Fecha a traves de los milisegundos transcurridos desde el 01-01-1970
     * @param longFecha Milisegundos
     */
    public Fecha(long longFecha){
        setTime(longFecha);
    }

    /**
     * Obtenemos Fecha a traves de una fecha en String. Hay que especificar en que formato se lo entregamos
     * @param StringFecha Fecha
     * @param formato Formato de la fecha
     */
    public Fecha(String StringFecha, String formato){
        this.formato = formato;
        setTime(StringFecha);
    }


    // =================================================================================================================
    // ======================================== setTime / setFormato ===================================================
    // =================================================================================================================
    /**
     * MÃ©todo para modificar fecha actual.
     */
    public void setTime(){
        fecha = new Date().getTime();
    }
    /**
     * MÃ©todo para modificar fecha a la indicada.
     * El formato empleado sera el de <code>formato</code> del objeto.
     * Para podificarlo haz uso del metodo <code>setFormato()</code>
     * @param stringFecha Fecha
     */
    public void setTime(String stringFecha){
        this.fecha = obtenerDate(stringFecha).getTime();
    }
    /**
     * MÃ©todo para modificar fecha a la indicada.
     * @param dateFecha Fecha
     */
    public void setTime(Date dateFecha){
        this.fecha = dateFecha.getTime();
    }
    /**
     * MÃ©todo para modificar fecha a la indicada.
     * Indicaremos los milisegundos transcurridos desde el 01-01-1970
     * @param longFecha Fecha
     */
    public void setTime(long longFecha){
        this.fecha = longFecha;
    }

    public long getTime(){
        return this.fecha;
    }

    /**
     * MÃ©todo para modificar el formato predeterminado con la que trabaja la clase
     * @param stringFormato Formato de fecha
     */
    public void setFormato(String stringFormato){
        this.formato = stringFormato;
    }

    // =================================================================================================================
    // ======================================== obtenerFormato =========================================================
    // =================================================================================================================
    /**
     * Obtenemos un <code>Date</code> de la fecha del objeto.
     * @return Fecha en <code>Date</code>
     */
    public Date obtenerDate(){
        return new Date(this.fecha);
    }
    /**
     * Obtenemos un <code>Date</code> de una fecha en formato String.
     * EmplearÃ¡ el formato indicado del objeto.
     * @param stringFecha Fecha a parsear
     * @return Fecha en <code>Date</code>
     */
    public Date obtenerDate(String stringFecha){
        return obtenerDate(stringFecha, formato);
    }
    /**
     * MÃ©todo para obtener un Date a partir de una fecha String
     * @param fechaS Fecha en String
     * @param stringFormato Formato de la fecha
     * @return Date
     */
    public Date obtenerDate(String fechaS, String stringFormato){
        SimpleDateFormat sdf = getSimpleDateFormat(stringFormato);
        try {
            return sdf.parse(fechaS);
        } catch (ParseException ex) {
            System.err.println("ERROR SDF-Parse: " + ex);
            return null;
        }
    }

    /**
     * MÃ©todo para obtener un <code>Calendar</code> con la fecha del objeto
     * @return Calendario <code>Calendar</code>
     */
    public Calendar obtenerCalendar(){
        return obtenerCalendar(this.fecha);
    }

    /**
     * Metodo para obtener un <code>Calendar</code> de una fecha indicada en milisegundos
     * @param longFecha Fecha en milisegundos
     * @return Calendario <code>Calendar</code>
     */
    public Calendar obtenerCalendar(long longFecha){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(longFecha);
        return calendar;
    }

    /**
     * MÃ©todo de formateo para obtener la fecha del objeto en String con respecto al
     * formato indicada en ella
     * @return Fecha en texto
     */
    public String obtenerString(){
        return obtenerString(obtenerDate());
    }

    /**
     * MÃ©todo de formateo para obtener la fecha del objeto en String con respecto al
     * formato indicado.
     * @param stringFormato Formato de obtenciÃ³n de la fecha
     * @return Fecha en texto
     */
    public String obtenerString(String stringFormato){
        return obtenerString(obtenerDate(),stringFormato);
    }

    /**
     * MÃ©todo para obtener la fecha escrita de un <code>Date</code>. El formato obtenido serÃ¡
     * con respecto al indicado en el objeto.
     * @param dateFecha Fecha a formatear.
     * @return Fecha en texto
     */
    public String obtenerString(Date dateFecha){
        return obtenerString(dateFecha,this.formato);
    }
    /**
     * MÃ©todo para obtener la fecha en texto de un <code>Date</code> en el formato indicado.
     * @param dateFecha Fecha a formatear.
     * @param stringFormato Formato de salida
     * @return Fecha en texto
     */
    public String obtenerString(Date dateFecha, String stringFormato){
        return getSimpleDateFormat(stringFormato).format(dateFecha);
    }

    @Override
    public String toString(){
        return obtenerString(FORMATO_ALL);
    }


    // =================================================================================================================
    // ======================================== getElemento ============================================================
    // =================================================================================================================
    /**
     * Dia del mes de la fecha
     * @return Dia
     */
    public int getDia(){
        return obtenerCalendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Mes de la fecha. Ojo, se empieza desde el 0
     * Enero = 0, Febrero = 1 ... Diciembre = 11.
     * @return Mes
     */
    public int getMes(){
        return obtenerCalendar().get(Calendar.MONTH);
    }

    /**
     * AÃ±o de la fecha
     * @return AÃ±o
     */
    public int getYear(){
        return obtenerCalendar().get(Calendar.YEAR);
    }

    /**
     * Hora de la fecha
     * @return Hora
     */
    public int getHora(){
        return obtenerCalendar().get(Calendar.HOUR);
    }

    /**
     * Minutos de la fecha
     * @return Minutos
     */
    public int getMinutos(){
        return obtenerCalendar().get(Calendar.MINUTE);
    }

    /**
     * Segundos de la fecha
     * @return Segundos
     */
    public int getSegundos(){
        return obtenerCalendar().get(Calendar.SECOND);
    }

    /**
     * Dia de la semana de la fecha.
     * Se emplea el formato americano, por lo que la semana empieza en el domingo:
     * Domingo = 1, Lunes = 2 ... Sabado = 7.
     * @return Dia de la semana
     */
    public int getSemana(){
        return obtenerCalendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Nombre del dia de la semana de la fecha.
     * En espaÃ±ol
     * @return Nombre del dia de la semana
     */
    public String getSemanaNombre(){
        return nomSemana[getSemana()-1];
    }

    /**
     * Nombre del mes de la fecha.
     * En espaÃ±ol
     * @return Nombre del mes
     */
    public String getMesNombreES(){
        return nomMeses[getMes()-1];
    }

    /**
     * Nombre del mes de la fecha.
     * Codigo de 3 caracteres en ingles
     * @return Codigo del mes en ingles
     */
    public String getMesNombreING(){
        return getSimpleDateFormat("MMM").format(obtenerDate());
    }

    /**
     * Dias totales del mes de la fecha
     * @return Dias totales del mes
     */
    public int getDiasTotalesMes(){
        return obtenerCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    // =================================================================================================================
    // ======================================== setElemento ============================================================
    // =================================================================================================================
    /**
     * Modificar el dia del mes.
     * @param dia Dia a seleccionar
     */
    public void setDia(int dia){
        setFecha(Calendar.DAY_OF_MONTH,dia);
    }

    /**
     * Modificar el mes
     * @param mes Mes a seleccionar
     */
    public void setMes(int mes){
        setFecha(Calendar.MONTH,mes);
    }

    /**
     * Modificar el aÃ±o
     * @param year AÃ±o a seleccionar
     */
    public void setYear(int year){
        setFecha(Calendar.YEAR,year);
    }

    /**
     * Modificar la hora
     * @param hora Hora a seleccionar
     */
    public void setHora(int hora){
        setFecha(Calendar.HOUR,hora);
    }

    /**
     * Modificar los minutos
     * @param minutos Minutos a seleccionar
     */
    public void setMinutos(int minutos){
        setFecha(Calendar.MINUTE,minutos);
    }

    /**
     * Modificar los segundos
     * @param segundos Segundos a seleccionar
     */
    public void setSegundos(int segundos){
        setFecha(Calendar.SECOND,segundos);
    }

    /**
     * MÃ©todo principal empleada por la familia de metodos <code>set(elemento)</code> y en la que se apoya el
     * metodo principal de la familia de add <code>addFecha</code>.
     * CambiarÃ¡, al elemento de la fecha que se indicado, su valor por el indicado
     * @param elemento Elemento al que modificar
     * @param modificacion Valor por el que sutituir
     */
    private void setFecha(int elemento, int modificacion){
        Calendar calendar = obtenerCalendar();
        calendar.set(elemento,modificacion);
        this.fecha = calendar.getTimeInMillis();
    }


    // =================================================================================================================
    // ======================================== addElemento ============================================================
    // =================================================================================================================
    /**
     * AÃ±adir dias a la fecha. Si es negativo se restarÃ¡
     * @param dias Dias a aÃ±adir
     */
    public void addDia(int dias){
        addFecha(Calendar.DAY_OF_MONTH,dias);
    }

    /**
     * AÃ±adir meses a la fecha. Si es negativo se restarÃ¡
     * @param mes Meses a aÃ±adir
     */
    public void addMes(int mes){
        addFecha(Calendar.MONTH,mes);
    }

    /**
     * AÃ±adir aÃ±os. Si es negativo se restarÃ¡
     * @param year AÃ±os a aÃ±adir
     */
    public void addYear(int year){
        addFecha(Calendar.YEAR,year);
    }

    /**
     * AÃ±adir horas. Si es negativo se restarÃ¡
     * @param hora Horas a aÃ±adir
     */
    public void addHora(int hora){
        addFecha(Calendar.HOUR,hora);
    }

    /**
     * AÃ±adir minutos. Si es negativo se restarÃ¡
     * @param minutos Minutos a aÃ±adir
     */
    public void addMinutos(int minutos){
        addFecha(Calendar.MINUTE,minutos);
    }

    /**
     * AÃ±adir segundos. Si es negativo se restarÃ¡
     * @param segundos Segundos a aÃ±adir
     */
    public void addSegundos(int segundos){
        addFecha(Calendar.SECOND,segundos);
    }

    /**
     * Metodo principal empleado por la familia de metodos <code>add(elemento)</code>. En el, aÃ±adimos al elemento
     * indicado el valor indicado.
     * @param elemento Elemento al que aÃ±adir. Emplea el codigo de Calendar
     * @param add Numero al que se sumarÃ¡ a la fecha
     */
    private void addFecha(int elemento, int add){
        setFecha(elemento,obtenerCalendar().get(elemento)+add);
    }


    // =================================================================================================================
    // ======================================== restar/sumar Fechas ====================================================
    // =================================================================================================================
    /**
     * Restar 2 fechas, la indicada y la del objeto.
     * @deprecated La fecha minima es 01-01-1970. El resultado puede no ser el deseado. Mejor use las funciones diffFechas
     * @param stringFecha Fecha a restar
     */
    public void restarFecha(String stringFecha){
        sumarFecha(-obtenerDate(stringFecha).getTime());
    }
    /**
     * Restar 2 fechas, la indicada y la del objeto.
     * @deprecated La fecha minima es 01-01-1970. El resultado puede no ser el deseado. Mejor use las funciones diffFechas
     * @param dateFecha Fecha a restar
     */
    public void restarFecha(Date dateFecha){
        sumarFecha(-dateFecha.getTime());
    }
    /**
     * Suma 2 fechas, al inndicada y la del objeto.
     * @param stringFecha Fecha a sumar
     */
    public void sumarFecha(String stringFecha){
        sumarFecha(obtenerDate(stringFecha).getTime());
    }
    /**
     * Suma 2 fechas, la indicada y la del objeto.
     * @param dateFecha Fecha a restar
     */
    public void sumarFecha(Date dateFecha){
        sumarFecha(dateFecha.getTime());
    }

    // =================================================================================================================
    // ======================================== diffFechasPeriodo ======================================================
    // =================================================================================================================
    /**
     * Calcula la diferencia que existe entre la fecha del objeto y la fecha entregada
     * DevolverÃ¡ un Period que dentro tendrÃ¡ los dias, meses y aÃ±os que han trascurrido
     * entre las 2 fechas.
     * @param fecha Fecha con la que calcular la diferencia
     * @return Diferencia entre ambas fechas
     */
    public Period diffFechasPeriod(Fecha fecha){
        return diffFechasPeriod(fecha.obtenerString(),this.obtenerString());
    }

    /**
     * Calcula la diferencia que existe entre la fecha del objeto y la fecha entregada
     * DevolverÃ¡ un Period que dentro tendrÃ¡ los dias, meses y aÃ±os que han trascurrido
     * entre las 2 fechas.
     * @param stringFecha Fecha con la que calcular la diferencia
     * @return Diferencia entre ambas fechas
     */
    public Period diffFechasPeriod(String stringFecha){
        return diffFechasPeriod(stringFecha,obtenerString());
    }
    /**
     * Calcula la diferencia que existe entre 2 fechas entregadas
     * DevolverÃ¡ un Period que dentro tendrÃ¡ los dias, meses y aÃ±os que han trascurrido
     * entre las 2 fechas.
     * @param stringFecha1 Primera fecha con la que calcular la diferencia
     * @param stringFecha2 Segunda fecha con la que calcular la diferencia
     * @return Diferencia entre ambas fechas
     */
    public Period diffFechasPeriod(String stringFecha1, String stringFecha2){
        DateTimeFormatter dtf = getDateTimeFormatter();
        return diffFechasPeriod(LocalDate.parse(stringFecha1,dtf),LocalDate.parse(stringFecha2,dtf));
    }

    /**
     * Calcula la diferencia que existe entre la fecha del objeto y la fecha entregada
     * DevolverÃ¡ un Period que dentro tendrÃ¡ los dias, meses y aÃ±os que han trascurrido
     * entre las 2 fechas.
     * @param dateFecha Fecha con la que calcular la diferencia
     * @return Diferencia entre ambas fechas
     */
    public Period diffFechasPeriod(Date dateFecha){
        return diffFechasPeriod(dateFecha,this.obtenerString());
    }

    /**
     * Calcula la diferencia que existe entre 2 fechas entregadas
     * DevolverÃ¡ un Period que dentro tendrÃ¡ los dias, meses y aÃ±os que han trascurrido
     * entre las 2 fechas.
     * @param dateFecha1 Primera fecha con la que calcular la diferencia
     * @param dateFecha2 Segunda fecha con la que calcular la diferencia
     * @return Diferencia entre ambas fechas
     */
    public Period diffFechasPeriod(Date dateFecha1,Date dateFecha2){
        return diffFechasPeriod(this.obtenerString(dateFecha1),this.obtenerString(dateFecha2));
    }

    /**
     * Calcula la diferencia que existe entre 2 fechas entregadas
     * DevolverÃ¡ un Period que dentro tendrÃ¡ los dias, meses y aÃ±os que han trascurrido
     * entre las 2 fechas.
     * @param dateFecha Primera fecha con la que calcular la diferencia
     * @param stringFecha Segunda fecha con la que calcular la diferencia
     * @return Diferencia entre ambas fechas
     */
    public Period diffFechasPeriod(Date dateFecha,String stringFecha){
        return diffFechasPeriod(this.obtenerString(dateFecha),stringFecha);
    }

    /**
     * Metodo privado principal que calcularÃ¡ el periodo que existe entre 2 tiempos locales.
     * Este metodo es el empleado en todas las funciones de la familia <code>diffFechasPeriodo</code>
     * @param localFecha1 Primera fecha del periodo
     * @param localFecha2 Segunda fecha del periodo
     * @return Periodo que hay entre ambas fechas
     */
    private Period diffFechasPeriod(LocalDate localFecha1, LocalDate localFecha2){
        return Period.between(localFecha1,localFecha2);
    }


    // =================================================================================================================
    // ======================================== diffFechasDias =========================================================
    // =================================================================================================================
    /**
     * Calcula los dias que hay entre 2 fechas. Nos devolverÃ¡ un numero entero redondeado con los dias
     * que existen entre 2 fechas. DevolverÃ¡ un numero absoluto.
     * TomarÃ¡ como una de las fechas la almacenada en el objeto.
     * @param fecha Fecha a calcular
     * @return Numero de dias entre las fechas
     */
    public int diffFechasDias(Fecha fecha){
        return diffFechasDias(fecha.getTime(),this.getTime());
    }

    /**
     * Calcula los dias que hay entre 2 fechas. Nos devolverÃ¡ un numero entero redondeado con los dias
     * que existen entre 2 fechas. DevolverÃ¡ un numero absoluto.
     * TomarÃ¡ como una de las fechas la almacenada en el objeto.
     * @param stringFecha Fecha a calcular
     * @return Numero de dias entre las fechas
     */
    public int diffFechasDias(String stringFecha){
        return diffFechasDias(stringFecha,this.obtenerDate());
    }

    /**
     * Calcula los dias que hay entre 2 fechas. Nos devolverÃ¡ un numero entero redondeado con los dias
     * que existen entre 2 fechas. DevolverÃ¡ un numero absoluto.
     * @param stringFecha1 primera fecha a calcular
     * @param stringFecha2 Segunda fecha a calcular
     * @return Numero de dias entre las fechas
     */
    public int diffFechasDias(String stringFecha1, String stringFecha2){
        return diffFechasDias(this.obtenerDate(stringFecha1).getTime(),this.obtenerDate(stringFecha2).getTime());
    }

    /**
     * Calcula los dias que hay entre 2 fechas. Nos devolverÃ¡ un numero entero redondeado con los dias
     * que existen entre 2 fechas. DevolverÃ¡ un numero absoluto.
     * @param stringFecha1 primera fecha a calcular
     * @param dateFecha2 Segunda fecha a calcular
     * @return Numero de dias entre las fechas
     */
    public int diffFechasDias(String stringFecha1, Date dateFecha2){
        return diffFechasDias(this.obtenerDate(stringFecha1).getTime(),this.getTime());
    }

    /**
     * Calcula los dias que hay entre 2 fechas. Nos devolverÃ¡ un numero entero redondeado con los dias
     * que existen entre 2 fechas. DevolverÃ¡ un numero absoluto.
     * TomarÃ¡ como una de las fechas la almacenada en el objeto.
     * @param dateFecha Fecha a calcular
     * @return Numero de dias entre las fechas
     */
    public int diffFechasDias(Date dateFecha){
        return diffFechasDias(dateFecha,obtenerDate());
    }
    /**
     * Calcula los dias que hay entre 2 fechas. Nos devolverÃ¡ un numero entero redondeado con los dias
     * que existen entre 2 fechas. DevolverÃ¡ un numero absoluto.
     * @param dateFecha1 primera fecha a calcular
     * @param dateFecha2 Segunda fecha a calcular
     * @return Numero de dias entre las fechas
     */
    public int diffFechasDias(Date dateFecha1, Date dateFecha2){
        return diffFechasDias(dateFecha1.getTime(),dateFecha2.getTime());
    }

    /**
     * MÃ©todo privado principal que calcularÃ¡ el numero de dias que existe entre 2 fechas.
     * Este mÃ©todo es el empleado en todas las funciones de la familia <code>diffFechasDias()</code>.
     * Este restarÃ¡ los milisegundos de las 2 fechas y lo dividirÃ¡ por los milisegundos que
     * tiene un dia (Definido con la variable <code>MILIS_DAY</code>)
     * @param longFecha1 Milisegundos de la primera fecha
     * @param longFecha2 Milisegundos de la segunda fecha
     * @return Numero de dias que existe entre las 2 fechas
     */
    private int diffFechasDias(long longFecha1, long longFecha2){
        return (int)(Math.abs(longFecha1 - longFecha2) / MILIS_DAY);
    }


    // =================================================================================================================
    // ======================================== dateRandow =============================================================
    // =================================================================================================================
    /**
     * DevolverÃ¡ una fecha aleatoria comprendida entre un minimo indicado y la fecha actual
     * @param min Fecha minima
     * @return Fecha aleatoria
     */
    public Date dateRandow(String min){
        return dateRandow(obtenerDate(min),obtenerDate());
    }

    /**
     * DevolverÃ¡ una fecha aleatoria comprendida entre un minimo indicado y la fecha maxima indicada
     * @param min Fecha minima
     * @param max Fecha maxima
     * @return Fecha aleatoria
     */
    public Date dateRandow(String min, String max){
        return dateRandow(obtenerDate(min),obtenerDate(max));
    }

    /**
     * DevolverÃ¡ una fecha aleatoria comprendida entre un minimo indicado y la fecha actual
     * @param min Fecha minima
     * @return Fecha aleatoria
     */
    public Date dateRandow(Date min){
        return dateRandow(min,obtenerDate());
    }

    /**
     * DevolverÃ¡ una fecha aleatoria entre el minimo y el maximo indicado.
     * El metodo al final comprobarÃ¡ si la entregada como minima es realmente minima,
     * sino lo tornarÃ¡
     * @param min Fecha minima
     * @param max Fecha maxima
     * @return Fecha aleatoria
     */
    public Date dateRandow(Date min, Date max){
        if (max.getTime() < min.getTime()){
            Date aux = min;
            min = max;
            max = aux;
        }
        long fechN = longRandow(max.getTime(), min.getTime());
        return new Date(fechN);
    }


    // =================================================================================================================
    // ======================================== Otros metodos ==========================================================
    // =================================================================================================================
    /**
     * DevolverÃ¡ los dias que caen el dia de la semana indicado
     * @param diaSemana Dia de la semana a buscar (Domingo = 1, Lunes = 2 ... Sabado = 7)
     * @return Lista de dias del mes en los que cae los dia de la semana elegido
     */
    public List<Integer> numsDiaSemanaEnMes(int diaSemana){
        List<Integer> numeros = new ArrayList<>();

        Calendar cale = obtenerCalendar();
        int daysMonth = getDiasTotalesMes();

        cale.set(Calendar.DAY_OF_MONTH,1);
        while(getSemana() != diaSemana)
            cale.add(Calendar.DAY_OF_YEAR, 1);

        for(int i=cale.get(Calendar.DAY_OF_MONTH); i<daysMonth+1;i+=7)
            numeros.add(i);

        return numeros;
    }


    // =================================================================================================================
    // ======================================== Metodos Auxiliares =====================================================
    // =================================================================================================================
    /**
     * MÃ©todo para obtener el <code>DateTimeFormatter</code> ya configurado al
     * formato indicado en <code>formato</code>
     * @return <code>DateTimeFormatter</code> ya configurado
     */
    private DateTimeFormatter getDateTimeFormatter(){
        return getDateTimeFormatter(this.formato);
    }

    /**
     * MÃ©todo para obtener el <code>DateTimeFormatter</code> ya configurado al
     * formato indicado.
     * @return <code>DateTimeFormatter</code> ya configurado
     */
    private DateTimeFormatter getDateTimeFormatter(String formatoS){
        return DateTimeFormatter.ofPattern(formatoS);
    }

    /**
     * MÃ©todo para obtener el <code>SimpleDateFormat</code> ya configurado al
     * formado indicado
     * @param formatoS Formato con el que configurar la clase
     * @return SimpleDateFormat ya configurado
     */
    private SimpleDateFormat getSimpleDateFormat(String formatoS){
        return new SimpleDateFormat(formatoS);
    }

    /**
     * MÃ©todo principal del los metodos <code>sumaFecha</code> y <code>restaFecha</code> (Depreciados). SumarÃ¡ a los
     * milisegundos de la fecha los milisegundos indicados
     * @param longFecha Milisegundos a sumar
     */
    private void sumarFecha(long longFecha){
        this.fecha += longFecha;
    }

    /**
     * MÃ©todo por el que obtener un numero long aleatorio comprendido entre 2 numero indicados.
     * Este metodo es empleado por la familia de metodos <code>dateRandow</code>.
     * @param sup Numero maximo que devolverÃ¡
     * @param inf Numero minimo que devolverÃ¡
     * @return Numero aleatorio comprendido entre los 2 margenes
     */
    private long longRandow(Long sup, Long inf){
        return (long)((Math.random()*(sup-inf)) + inf);
    }

}
