// Blue Print : Kontrak untuk class anak yang akan mengimplementasikan interface ini.
public interface BendaGeometri {

    // Method ini disiapkan untuk class anak yang akan mengimplementasikan interface ini (OVERRIDING).
    public double hitungLuas();
    public double hitungKeliling();

    // OVERLOADING : method sama, tapi menggunakan parameter.
    public double hitungLuas(double semiMayorLuar, double semiMinorLuar);
    public double hitungKeliling(double semiMayorLuar, double semiMinorLuar);
}
