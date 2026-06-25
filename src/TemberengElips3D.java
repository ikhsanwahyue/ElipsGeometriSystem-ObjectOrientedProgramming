// INHERITENCE : TemberengElips3D merupakan turunan dari BolaElips (3D) untuk menghitung volume dan luas permukaan tembereng elips 3D (prisma dengan alas tembereng elips).
// MULTHITREADING : implements Runnable agar dapat berjalan sebagai Thread mandiri untuk masing-masing bangun ruang.
public class TemberengElips3D extends BolaElips implements Runnable {

    public double sudutDerajat, tinggiTembereng, luasAlasTembereng, kelilingAlasTembereng, volumeTemberengElips3D, luasPermukaanTemberengElips3D;
    public double theta, panjangBusur, taliBusur, kelilingElips, integrandBusur, jumlahSegmen, h, total;

    // Constructor untuk menginisialisasi objek TemberengElips3D dengan parameter.
    public TemberengElips3D(double semiMayorLuar, double semiMinorLuar, double tinggiTembereng, double sudutDerajat, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, tinggiTembereng, isManual);
        
        // Validasi : Memastikan tinggi tembereng, sudut, dan sumbu-sumbu bernilai valid.
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

    // Menghitung volume tembereng elips 3D menggunakan nilai yang tersimpan.
    public double hitungVolumeTemberengElips3D() {
        this.theta = Math.toRadians(this.sudutDerajat);
        this.luasAlasTembereng = 0.5 * super.semiMayorLuar * super.semiMinorLuar * (theta - Math.sin(theta));
        this.volumeTemberengElips3D = this.luasAlasTembereng * this.tinggiTembereng;
        return this.volumeTemberengElips3D;
    }

    // OVERLOADING : method hitungVolumeTemberengElips3D dengan parameter baru.
    public double hitungVolumeTemberengElips3D(double semiMayorLuar, double semiMinorLuar, double tinggiTembereng, double sudutDerajat) {
        // Validasi parameter
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

    // Menghitung luas permukaan tembereng elips 3D (2*luas alas + keliling alas * tinggi) menggunakan nilai yang tersimpan.
    public double hitungLuasPermukaanTemberengElips3D() {
        this.theta = Math.toRadians(this.sudutDerajat);
        this.panjangBusur = hitungPanjangBusurElips(super.semiMayorLuar, super.semiMinorLuar, this.sudutDerajat);
        this.taliBusur = hitungTaliBusurElips(super.semiMayorLuar, super.semiMinorLuar, this.sudutDerajat);
        this.kelilingAlasTembereng = panjangBusur + taliBusur;
        this.luasAlasTembereng = 0.5 * super.semiMayorLuar * super.semiMinorLuar * (theta - Math.sin(theta));
        this.luasPermukaanTemberengElips3D = (2 * this.luasAlasTembereng) + (this.kelilingAlasTembereng * this.tinggiTembereng);
        return this.luasPermukaanTemberengElips3D;
    }

    // OVERLOADING : method hitungLuasPermukaanTemberengElips3D dengan parameter.
    public double hitungLuasPermukaanTemberengElips3D(double semiMayorLuar, double semiMinorLuar, double tinggiTembereng, double sudutDerajat) {
        // Validasi parameter
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

    // Menghitung panjang busur elips menggunakan metode Simpson (integrasi numerik).
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

    // Fungsi integrand untuk perhitungan panjang busur elips.
    private double integrandBusur(double semiMayorLuar, double semiMinorLuar, double t) {
        integrandBusur = Math.sqrt(
                Math.pow(semiMayorLuar * Math.sin(t), 2)
                + Math.pow(semiMinorLuar * Math.cos(t), 2)
        );
        return integrandBusur;
    }

    // Menghitung panjang tali busur dari tembereng elips.
    private double hitungTaliBusurElips(double semiMayorLuar, double semiMinorLuar, double sudutDerajat) {
        theta = Math.toRadians(sudutDerajat);
        taliBusur = Math.sqrt(
                Math.pow(semiMayorLuar * Math.cos(theta) - semiMayorLuar, 2)
                + Math.pow(semiMinorLuar * Math.sin(theta), 2)
        );
        return taliBusur;
    }

    // OVERRIDING : Implementasi method run() dari Runnable untuk menjalankan perhitungan sesuai mode isManual.
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