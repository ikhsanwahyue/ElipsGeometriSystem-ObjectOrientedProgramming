public class PrismaElips extends Elips implements Runnable {

    public double tinggiPrisma, volumePrismaElips, luasPermukaanPrismaElips;
    public double luasAlas, kelilingAlas;
    
    public PrismaElips(double semiMayorLuar, double semiMinorLuar, double tinggiPrisma, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, isManual);
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma/tabung elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        this.tinggiPrisma = tinggiPrisma;
    }

    public double hitungVolumePrismaElips() {
        this.volumePrismaElips = super.luas * this.tinggiPrisma;
        return this.volumePrismaElips;
    }

    public double hitungVolumePrismaElips(double semiMayorLuar, double semiMinorLuar, double tinggiPrisma) {
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma/tabung elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        this.luasAlas = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.volumePrismaElips = luasAlas * tinggiPrisma;
        return this.volumePrismaElips;
    }

    public double hitungLuasPermukaanPrismaElips() {
        this.luasPermukaanPrismaElips = (2 * super.luas) + (super.keliling * this.tinggiPrisma);
        return this.luasPermukaanPrismaElips;
    }

    public double hitungLuasPermukaanPrismaElips(double semiMayorLuar, double semiMinorLuar, double tinggiPrisma) {
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma/tabung elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        this.luasAlas = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.kelilingAlas = super.hitungKeliling(semiMayorLuar, semiMinorLuar);
        this.luasPermukaanPrismaElips = (2 * luasAlas) + (kelilingAlas * tinggiPrisma);
        return this.luasPermukaanPrismaElips;
    }

    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasPermukaanPrismaElips(semiMayorLuar, semiMinorLuar, tinggiPrisma);
            this.hitungVolumePrismaElips(semiMayorLuar, semiMinorLuar, tinggiPrisma);
        } else {
            this.hitungLuasPermukaanPrismaElips();
            this.hitungVolumePrismaElips();
        }
    }
}
