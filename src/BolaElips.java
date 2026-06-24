public class BolaElips extends Elips implements Runnable {

    public double sumbuC, volumeBolaElips, luasPermukaanBolaElips, luasAlas;
    public final double p = 1.6075;

    public BolaElips(double semiMayorLuar, double semiMinorLuar, double sumbuC, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, isManual);
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai semi mayor dan semi minor bola elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Nilai semi mayor harus lebih besar atau sama dengan semi minor bola elips!");
        }
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        this.sumbuC = sumbuC;
    }

    public double hitungVolumeBolaElips() {
        if (super.luas <= 0) {
            throw new IllegalArgumentException("Nilai luas harus lebih besar dari 0!"); 
        }
        this.volumeBolaElips = (4.0 / 3.0) * super.luas * this.sumbuC;
        return this.volumeBolaElips;
    }

    public double hitungVolumeBolaElips(double semiMayorLuar, double semiMinorLuar, double sumbuC) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai semi mayor dan semi minor bola elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Nilai semi mayor harus lebih besar atau sama dengan semi minor bola elips!");
        }
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        luasAlas = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.volumeBolaElips = (4.0 / 3.0) * luasAlas * sumbuC;
        return this.volumeBolaElips;
    }

    public double hitungLuasPermukaanBolaElips() {
        this.luasPermukaanBolaElips = 4 * Math.PI * Math.pow(
                (Math.pow(super.semiMayorLuar * super.semiMinorLuar, p)
                + Math.pow(super.semiMayorLuar * this.sumbuC, p)
                + Math.pow(super.semiMinorLuar * this.sumbuC, p)) / 3.0,
                1.0 / p
        );
        return this.luasPermukaanBolaElips;
    }

    public double hitungLuasPermukaanBolaElips(double semiMayorLuar, double semiMinorLuar, double sumbuC) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Nilai semi mayor dan semi minor bola elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Nilai semi mayor harus lebih besar atau sama dengan semi minor bola elips!");
        }
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        this.luasPermukaanBolaElips = 4 * Math.PI * Math.pow(
                (Math.pow(semiMayorLuar * semiMinorLuar, p)
                + Math.pow(semiMayorLuar * sumbuC, p)
                + Math.pow(semiMinorLuar * sumbuC, p)) / 3.0,
                1.0 / p
        );
        return this.luasPermukaanBolaElips;
    }

    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasPermukaanBolaElips(semiMayorLuar, semiMinorLuar, sumbuC);
            this.hitungVolumeBolaElips(semiMayorLuar, semiMinorLuar, sumbuC);
        } else {
            this.hitungLuasPermukaanBolaElips();
            this.hitungVolumeBolaElips();
        }
    }
}