// INHERITENCE : TemberengElips merupakan turunan dari Elips (2D) untuk menghitung luas dan keliling tembereng elips.
// MULTHITREADING : implements Runnable agar dapat berjalan sebagai Thread mandiri.
public class TemberengElips extends Elips implements Runnable {

    public double sudutDerajat, luasTembereng, kelilingTembereng;
    public double theta, panjangBusur, taliBusur, kelilingElips, integrandBusur, jumlahSegmen, h, total;

    // Constructor untuk menginisialisasi objek TemberengElips dengan parameter.
    public TemberengElips(double semiMayorLuar, double semiMinorLuar, double sudutDerajat, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, isManual);
        
        // Validasi : Memastikan sudut tembereng dan sumbu-sumbu bernilai valid.
        if (sudutDerajat <= 0 || sudutDerajat >= 360) {
            throw new IllegalArgumentException("Sudut tembereng elips harus lebih dari 0 dan kurang dari 360 derajat!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }

        this.sudutDerajat = sudutDerajat;
    }

    // OVERRIDING : Implementasi method hitungLuas() dari superclass Elips untuk menghitung luas tembereng.
    @Override
    public double hitungLuas() {
        return this.hitungLuasTembereng();
    }

    // OVERRIDING : Implementasi method hitungKeliling() dari superclass Elips untuk menghitung keliling tembereng.
    @Override
    public double hitungKeliling() {
        return this.hitungKelilingTembereng();
    }

    // Menghitung luas tembereng menggunakan nilai sudut dan sumbu yang tersimpan.
    public double hitungLuasTembereng() {
        theta = Math.toRadians(this.sudutDerajat);
        this.luasTembereng = 0.5 * super.semiMayorLuar * super.semiMinorLuar * (theta - Math.sin(theta));
        return this.luasTembereng;
    }

    // OVERLOADING : method hitungLuasTembereng dengan parameter baru.
    public double hitungLuasTembereng(double semiMayorLuar, double semiMinorLuar, double sudutDerajat) {
        // Validasi parameter
        if (sudutDerajat <= 0 || sudutDerajat >= 360) {
            throw new IllegalArgumentException("Sudut tembereng elips harus lebih dari 0 dan kurang dari 360 derajat!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        theta = Math.toRadians(sudutDerajat);
        this.luasTembereng = 0.5 * semiMayorLuar * semiMinorLuar * (theta - Math.sin(theta));
        return this.luasTembereng;
    }

    // Menghitung keliling tembereng (panjang busur + tali busur) menggunakan nilai yang tersimpan.
    public double hitungKelilingTembereng() {
        this.theta = Math.toRadians(this.sudutDerajat);
        this.panjangBusur = hitungPanjangBusurElips(super.semiMayorLuar, super.semiMinorLuar, this.sudutDerajat);
        this.taliBusur = hitungTaliBusurElips(super.semiMayorLuar, super.semiMinorLuar, this.sudutDerajat);
        this.kelilingTembereng = panjangBusur + taliBusur;
        return this.kelilingTembereng;
    }

    // OVERLOADING : method hitungKelilingTembereng dengan parameter.
    public double hitungKelilingTembereng(double semiMayorLuar, double semiMinorLuar, double sudutDerajat) {
        // Validasi parameter
        if (sudutDerajat <= 0 || sudutDerajat >= 360) {
            throw new IllegalArgumentException("Sudut tembereng elips harus lebih dari 0 dan kurang dari 360 derajat!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        this.kelilingElips = super.hitungKeliling(semiMayorLuar, semiMinorLuar);
        this.theta = Math.toRadians(sudutDerajat);
        this.panjangBusur = hitungPanjangBusurElips(semiMayorLuar, semiMinorLuar, sudutDerajat);
        this.taliBusur = hitungTaliBusurElips(semiMayorLuar, semiMinorLuar, sudutDerajat);
        this.kelilingTembereng = panjangBusur + taliBusur;
        return this.kelilingTembereng;
    }

    // Menghitung panjang busur elips menggunakan metode Simpson (integrasi numerik).
    private double hitungPanjangBusurElips(double semiMayorLuar, double semiMinorLuar, double sudutDerajat) {
        double theta = Math.toRadians(sudutDerajat);
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
        integrandBusur = Math.sqrt(Math.pow(-semiMayorLuar * Math.sin(t), 2) + Math.pow(semiMinorLuar * Math.cos(t), 2));
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
        if (this.isManual) {
            this.hitungLuasTembereng(semiMayorLuar, semiMinorLuar, sudutDerajat);
            this.hitungKelilingTembereng(semiMayorLuar, semiMinorLuar, sudutDerajat);
        } else {
            this.hitungLuas();
            this.hitungKeliling();
        }
    }
}