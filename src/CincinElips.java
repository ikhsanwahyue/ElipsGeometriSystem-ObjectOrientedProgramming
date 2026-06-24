// INHERITENCE : CincinElips (2D) turunan dari Elips (2D).
// MULTHITREADING : "implements Runnable" agar bisa berjalan sebagai Thread mandiri untuk masing-masing bangun ruang.
public class CincinElips extends Elips implements Runnable {

    // ENCAPSULATION : Atribut state untuk membungkus hasil perhitungan luas dan keliling cincin elips.
    public double semiMayorDalam, semiMinorDalam;
    public double luasCincin, kelilingCincin, luasElips, kelilingDalam, kelilingElips, luasDalam;

    // Constructor untuk menginisialisasi atribut state cincin elips.
    public CincinElips(double semiMayorLuar, double semiMinorLuar, double semiMayorDalam, double semiMinorDalam, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, isManual); // INHERITENCE : Memanggil constructor parent class (Elips) untuk menginisialisasi semiMayorLuar, semiMinorLuar, dan isManual.
        
        // VALIDASI : Memastikan nilai semi mayor dan semi minor dalam lebih kecil dari semi mayor dan semi minor luar, serta lebih besar dari 0.
        if (semiMayorDalam <= 0 || semiMinorDalam <= 0) {
            throw new IllegalArgumentException("Semi mayor dalam dan semi minor dalam cincin elips harus lebih besar dari 0!");
        }
        if (semiMayorDalam >= semiMayorLuar || semiMinorDalam >= semiMinorLuar) {
            throw new IllegalArgumentException("Elips dalam harus lebih kecil dari elips luar!");
        }
        if (semiMayorDalam < semiMinorDalam) {
            throw new IllegalArgumentException("Semi mayor dalam tidak boleh lebih kecil dari semi minor dalam!");
        }
        this.semiMayorDalam = semiMayorDalam;
        this.semiMinorDalam = semiMinorDalam;
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------
    // OVERRIDING : Implementasi method hitungLuas() dan hitungKeliling() dari interface BendaGeometri.
    @Override
    public double hitungLuas() {
        return this.hitungLuasCincin();
    }

    // OVERRIDING : Implementasi method hitungLuas() dan hitungKeliling() dari interface BendaGeometri.
    @Override
    public double hitungKeliling() {
        return this.hitungKelilingCincin();
    }

    // Method Baru : method hitungLuasCincin() untuk menghitung luas cincin elips (tanpa parameter/menggunakan data internal).
    public double hitungLuasCincin() {
        this.luasElips = super.hitungLuas(super.semiMayorLuar, super.semiMinorLuar);
        this.luasDalam = super.hitungLuas(this.semiMayorDalam, this.semiMinorDalam);
        this.luasCincin = this.luasElips - this.luasDalam;
        return this.luasCincin;
    }

    // OVERLOADING : Implementasi method hitungLuas() dan hitungKeliling() dengan parameter dari interface BendaGeometri.
    public double hitungLuasCincin(double semiMayorLuar, double semiMinorLuar, double semiMayorDalam, double semiMinorDalam) {
        if (semiMayorDalam <= 0 || semiMinorDalam <= 0) {
            throw new IllegalArgumentException("Semi mayor dalam dan semi minor dalam cincin elips harus lebih besar dari 0!");
        }
        if (semiMayorDalam >= semiMayorLuar || semiMinorDalam >= semiMinorLuar) {
            throw new IllegalArgumentException("Elips dalam harus lebih kecil dari elips luar!");
        }
        if (semiMayorDalam < semiMinorDalam) {
            throw new IllegalArgumentException("Semi mayor dalam tidak boleh lebih kecil dari semi minor dalam!");
        }
        this.luasElips = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.luasDalam = super.hitungLuas(semiMayorDalam, semiMinorDalam);
        super.luas = this.luasElips;
        this.luasCincin = this.luasElips - this.luasDalam;
        return this.luasCincin;
    }

    // Method Baru : method hitungKelilingCincin() untuk menghitung keliling cincin elips (tanpa parameter/menggunakan data internal).
    public double hitungKelilingCincin() {
        this.kelilingElips = super.hitungKeliling(super.semiMayorLuar, super.semiMinorLuar);
        this.kelilingDalam = super.hitungKeliling(this.semiMayorDalam, this.semiMinorDalam);
        super.keliling = this.kelilingElips;
        this.kelilingCincin = this.kelilingElips + this.kelilingDalam;
        return this.kelilingCincin;
    }

    // OVERLOADING : Implementasi method hitungKelilingCincin() dengan parameter dari interface BendaGeometri.
    public double hitungKelilingCincin(double semiMayorLuar, double semiMinorLuar, double semiMayorDalam, double semiMinorDalam) {

        // Validasi : Memastikan nilai semi mayor dan semi minor dalam lebih kecil dari semi mayor dan semi minor luar, serta lebih besar dari 0.
        if (semiMayorDalam <= 0 || semiMinorDalam <= 0) {
            throw new IllegalArgumentException("Semi mayor dalam dan semi minor dalam cincin elips harus lebih besar dari 0!");
        }
        if (semiMayorDalam >= semiMayorLuar || semiMinorDalam >= semiMinorLuar) {
            throw new IllegalArgumentException("Elips dalam harus lebih kecil dari elips luar!");
        }
        if (semiMayorDalam < semiMinorDalam) {
            throw new IllegalArgumentException("Semi mayor dalam tidak boleh lebih kecil dari semi minor dalam!");
        }
        this.kelilingElips = super.hitungKeliling(semiMayorLuar, semiMinorLuar);
        this.kelilingDalam = super.hitungKeliling(semiMayorDalam, semiMinorDalam);
        super.keliling = this.kelilingElips;
        this.kelilingCincin = this.kelilingElips + this.kelilingDalam;
        return this.kelilingCincin;
    }
    
    // OVERRIDING : method run() milik Runnable untuk menjalankan perhitungan luas dan keliling cincin elips secara otomatis saat thread dijalankan.
    @Override
    public void run() {
        if (this.isManual) {
            this.hitungLuasCincin(semiMayorLuar, semiMinorLuar, semiMayorDalam, semiMinorDalam);
            this.hitungKelilingCincin(semiMayorLuar, semiMinorLuar, semiMayorDalam, semiMinorDalam);
        } else {
            this.hitungLuas();
            this.hitungKeliling();
        }
    }
}