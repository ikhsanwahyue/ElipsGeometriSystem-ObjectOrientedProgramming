public class CincinElips3D extends BolaElips implements Runnable {

    public double radiusDalam, volumeDalam, luasPermukaanDalam, volumeCincinElips3D, luasPermukaanCincinElips3D;
    public double luasElips, kelilingElips, volumeLuar, luasPermukaanLuar, luasLingkaranDalam, kelilingLingkaranDalam;

    public CincinElips3D(double semiMayorLuar, double semiMinorLuar, double sumbuC, double radiusDalam, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, sumbuC, isManual);
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai semi mayor dan semi minor cincin elips 3D harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Nilai semi mayor harus lebih besar atau sama dengan semi minor cincin elips 3D!");
        }
        if (radiusDalam <= 0) {
            throw new IllegalArgumentException("Radius dalam cincin elips 3D harus lebih besar dari 0!");
        }
        if (radiusDalam >= semiMayorLuar || radiusDalam >= semiMinorLuar) {
            throw new IllegalArgumentException("Radius dalam cincin elips 3D harus lebih kecil dari semi mayor dan semi minor luar!");
        }
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        this.radiusDalam = radiusDalam;
    }        

    public double hitungVolumeCincinElips3D() {
        this.luasElips = super.hitungLuas(super.semiMayorLuar, super.semiMinorLuar);
        this.luasLingkaranDalam = PI * this.radiusDalam * this.radiusDalam;

        this.volumeLuar = this.luasElips * super.sumbuC;
        this.volumeDalam = luasLingkaranDalam * super.sumbuC;
        this.volumeCincinElips3D = this.volumeLuar - this.volumeDalam;
        return this.volumeCincinElips3D;
    }

    public double hitungVolumeCincinElips3D(double semiMayorLuar, double semiMinorLuar, double sumbuC, double radiusDalam) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai semi mayor dan semi minor cincin elips 3D harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Nilai semi mayor harus lebih besar atau sama dengan semi minor cincin elips 3D!");
        }
        if (radiusDalam <= 0) {
            throw new IllegalArgumentException("Radius dalam cincin elips 3D harus lebih besar dari 0!");
        }
        if (radiusDalam >= semiMayorLuar || radiusDalam >= semiMinorLuar) {
            throw new IllegalArgumentException("Radius dalam cincin elips 3D harus lebih kecil dari semi mayor dan semi minor luar!");
        }
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        this.luasElips = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.luasLingkaranDalam = PI * radiusDalam * radiusDalam;

        this.volumeLuar = this.luasElips * sumbuC;
        this.volumeDalam = luasLingkaranDalam * sumbuC;
        this.volumeCincinElips3D = this.volumeLuar - this.volumeDalam;
        return this.volumeCincinElips3D;
    }

    public double hitungLuasPermukaanCincinElips3D() {
        this.luasElips = super.hitungLuas(super.semiMayorLuar, super.semiMinorLuar);
        this.kelilingElips = super.hitungKeliling(super.semiMayorLuar, super.semiMinorLuar);

        this.luasLingkaranDalam = PI * this.radiusDalam * this.radiusDalam;
        this.kelilingLingkaranDalam = 2 * PI * this.radiusDalam;

        this.luasPermukaanLuar = (2 * this.luasElips) + (this.kelilingElips * super.sumbuC);
        this.luasPermukaanDalam = kelilingLingkaranDalam * super.sumbuC;
        this.luasPermukaanCincinElips3D = (2 * (this.luasElips - luasLingkaranDalam))
                + (this.kelilingElips * super.sumbuC)
                + this.luasPermukaanDalam;
        return this.luasPermukaanCincinElips3D;
    }

    public double hitungLuasPermukaanCincinElips3D(double semiMayorLuar, double semiMinorLuar, double sumbuC, double radiusDalam) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai semi mayor dan semi minor cincin elips 3D harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Nilai semi mayor harus lebih besar atau sama dengan semi minor cincin elips 3D!");
        }
        if (radiusDalam <= 0) {
            throw new IllegalArgumentException("Radius dalam cincin elips 3D harus lebih besar dari 0!");
        }
        if (radiusDalam >= semiMayorLuar || radiusDalam >= semiMinorLuar) {
            throw new IllegalArgumentException("Radius dalam cincin elips 3D harus lebih kecil dari semi mayor dan semi minor luar!");
        }
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        this.luasElips = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.kelilingElips = super.hitungKeliling(semiMayorLuar, semiMinorLuar);

        this.luasLingkaranDalam = PI * radiusDalam * radiusDalam;
        this.kelilingLingkaranDalam = 2 * PI * radiusDalam;

        this.luasPermukaanLuar = (2 * this.luasElips) + (this.kelilingElips * sumbuC);
        this.luasPermukaanDalam = this.kelilingLingkaranDalam * sumbuC;
        this.luasPermukaanCincinElips3D = (2 * (this.luasElips - this.luasLingkaranDalam))
                + (this.kelilingElips * sumbuC)
                + this.luasPermukaanDalam;
        return this.luasPermukaanCincinElips3D;
    }

    @Override
    public void run() {
        if (this.isManual) {
            this.hitungLuasPermukaanCincinElips3D(semiMayorLuar, semiMinorLuar, sumbuC, radiusDalam);
            this.hitungVolumeCincinElips3D(semiMayorLuar, semiMinorLuar, sumbuC, radiusDalam);
        } else {
            this.hitungLuasPermukaanCincinElips3D();
            this.hitungVolumeCincinElips3D();
        }
    }
}
