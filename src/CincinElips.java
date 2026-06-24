public class CincinElips extends Elips implements Runnable {

    public double semiMayorDalam, semiMinorDalam;
    public double luasCincin, kelilingCincin, luasElips, kelilingDalam, kelilingElips, luasDalam;

    public CincinElips(double semiMayorLuar, double semiMinorLuar, double semiMayorDalam, double semiMinorDalam, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, isManual);
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

    @Override
    public double hitungLuas() {
        return this.hitungLuasCincin();
    }

    @Override
    public double hitungKeliling() {
        return this.hitungKelilingCincin();
    }

    public double hitungLuasCincin() {
        this.luasElips = super.hitungLuas(super.semiMayorLuar, super.semiMinorLuar);
        this.luasDalam = super.hitungLuas(this.semiMayorDalam, this.semiMinorDalam);
        this.luasCincin = this.luasElips - this.luasDalam;
        return this.luasCincin;
    }

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

    public double hitungKelilingCincin() {
        this.kelilingElips = super.hitungKeliling(super.semiMayorLuar, super.semiMinorLuar);
        this.kelilingDalam = super.hitungKeliling(this.semiMayorDalam, this.semiMinorDalam);
        super.keliling = this.kelilingElips;
        this.kelilingCincin = this.kelilingElips + this.kelilingDalam;
        return this.kelilingCincin;
    }

    public double hitungKelilingCincin(double semiMayorLuar, double semiMinorLuar, double semiMayorDalam, double semiMinorDalam) {
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
