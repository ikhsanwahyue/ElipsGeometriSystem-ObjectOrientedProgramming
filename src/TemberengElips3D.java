public class TemberengElips3D extends BolaElips implements Runnable {

    public double sudutDerajat, tinggiTembereng, luasAlasTembereng, kelilingAlasTembereng, volumeTemberengElips3D, luasPermukaanTemberengElips3D;
    public double theta, panjangBusur, taliBusur, kelilingElips, integrandBusur, jumlahSegmen, h, total;

    // constructor
    public TemberengElips3D(double semiMayorLuar, double semiMinorLuar, double tinggiTembereng, double sudutDerajat, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, tinggiTembereng, isManual);
        if (tinggiTembereng <= 0) {
            throw new IllegalArgumentException("Tinggi tembereng elips 3D harus lebih besar dari 0!");
        }
        if (sudutDerajat <= 0 || sudutDerajat >= 360) {
            throw new IllegalArgumentException("Sudut tembereng elips 3D harus lebih dari 0 dan kurang dari 360 derajat!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }

        this.tinggiTembereng = tinggiTembereng;
        this.sudutDerajat = sudutDerajat;
    }

    // overriding
    public double hitungVolumeTemberengElips3D() {
        this.theta = Math.toRadians(this.sudutDerajat);
        this.luasAlasTembereng = 0.5 * super.semiMayorLuar * super.semiMinorLuar * (theta - Math.sin(theta));
        this.volumeTemberengElips3D = this.luasAlasTembereng * this.tinggiTembereng;
        return this.volumeTemberengElips3D;
    }

    // overloading
    public double hitungVolumeTemberengElips3D(double semiMayorLuar, double semiMinorLuar, double tinggiTembereng, double sudutDerajat) {
        if (tinggiTembereng <= 0) {
            throw new IllegalArgumentException("Tinggi tembereng elips 3D harus lebih besar dari 0!");
        }
        if (sudutDerajat <= 0 || sudutDerajat >= 360) {
            throw new IllegalArgumentException("Sudut tembereng elips 3D harus lebih dari 0 dan kurang dari 360 derajat!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        this.theta = Math.toRadians(sudutDerajat);
        this.luasAlasTembereng = 0.5 * semiMayorLuar * semiMinorLuar * (theta - Math.sin(theta));
        this.volumeTemberengElips3D = this.luasAlasTembereng * tinggiTembereng;
        return this.volumeTemberengElips3D;
    }

    // same as above
    public double hitungLuasPermukaanTemberengElips3D() {
        this.theta = Math.toRadians(this.sudutDerajat);
        this.panjangBusur = hitungPanjangBusurElips(super.semiMayorLuar, super.semiMinorLuar, this.sudutDerajat);
        this.taliBusur = hitungTaliBusurElips(super.semiMayorLuar, super.semiMinorLuar, this.sudutDerajat);
        this.kelilingAlasTembereng = panjangBusur + taliBusur;
        this.luasAlasTembereng = 0.5 * super.semiMayorLuar * super.semiMinorLuar * (theta - Math.sin(theta));
        this.luasPermukaanTemberengElips3D = (2 * this.luasAlasTembereng) + (this.kelilingAlasTembereng * this.tinggiTembereng);
        return this.luasPermukaanTemberengElips3D;
    }

    // same as above
    public double hitungLuasPermukaanTemberengElips3D(double semiMayorLuar, double semiMinorLuar, double tinggiTembereng, double sudutDerajat) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        if (sudutDerajat <= 0 || sudutDerajat >= 360) {
            throw new IllegalArgumentException("Sudut tembereng elips 3D harus lebih dari 0 dan kurang dari 360 derajat!");
        }
        if (tinggiTembereng <= 0) {
            throw new IllegalArgumentException("Tinggi tembereng elips 3D harus lebih besar dari 0!");
        }

        this.kelilingElips = super.hitungKeliling(semiMayorLuar, semiMinorLuar);
        this.theta = Math.toRadians(sudutDerajat);
        this.panjangBusur = hitungPanjangBusurElips(semiMayorLuar, semiMinorLuar, sudutDerajat);
        this.taliBusur = hitungTaliBusurElips(semiMayorLuar, semiMinorLuar, sudutDerajat);
        this.kelilingAlasTembereng = panjangBusur + taliBusur;
        this.luasAlasTembereng = 0.5 * semiMayorLuar * semiMinorLuar * (theta - Math.sin(theta));
        this.luasPermukaanTemberengElips3D = (2 * this.luasAlasTembereng) + (this.kelilingAlasTembereng * tinggiTembereng);
        return this.luasPermukaanTemberengElips3D;
    }

    private double hitungPanjangBusurElips(double semiMayorLuar, double semiMinorLuar, double sudutDerajat) {
        theta = Math.toRadians(sudutDerajat);
        jumlahSegmen = 1000;
        h = theta / jumlahSegmen;
        total = integrandBusur(semiMayorLuar, semiMinorLuar, 0)
                + integrandBusur(semiMayorLuar, semiMinorLuar, theta);

        for (int i = 1; i < jumlahSegmen; i++) {
            double t = i * h;
            total += (i % 2 == 0 ? 2 : 4) * integrandBusur(semiMayorLuar, semiMinorLuar, t);
        }

        panjangBusur = total * h / 3.0;
        return panjangBusur;
    }

    private double integrandBusur(double semiMayorLuar, double semiMinorLuar, double t) {
        integrandBusur = Math.sqrt(
                Math.pow(semiMayorLuar * Math.sin(t), 2)
                + Math.pow(semiMinorLuar * Math.cos(t), 2)
        );
        return integrandBusur;
    }

    private double hitungTaliBusurElips(double semiMayorLuar, double semiMinorLuar, double sudutDerajat) {
        theta = Math.toRadians(sudutDerajat);
        taliBusur = Math.sqrt(
                Math.pow(semiMayorLuar * Math.cos(theta) - semiMayorLuar, 2)
                + Math.pow(semiMinorLuar * Math.sin(theta), 2)
        );
        return taliBusur;
    }

    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasPermukaanTemberengElips3D(semiMayorLuar, semiMinorLuar, tinggiTembereng, sudutDerajat);
            this.hitungVolumeTemberengElips3D(semiMayorLuar, semiMinorLuar, tinggiTembereng, sudutDerajat);
        } else {
            this.hitungLuasPermukaanTemberengElips3D();
            this.hitungVolumeTemberengElips3D();
        }
    }
}
