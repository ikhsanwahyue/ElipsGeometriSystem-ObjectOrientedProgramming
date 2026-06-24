public class JuringElips extends Elips implements Runnable {

    public double sudutDerajat, luasJuring, kelilingJuring, luasElips, kelilingElips, panjangBusur, rataRataJariJari, panjangBusurElips;
    public double sisiAwal, sisiAkhir, theta, h, total, integrandBusur, jarakPusatKeTitikElips, jumlahSegmen;

    public JuringElips(double semiMayorLuar, double semiMinorLuar, double sudutDerajat, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, isManual);
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Semi mayor luar dan semi minor luar juring elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Semi mayor luar tidak boleh lebih kecil dari semi minor luar!");
        }
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips harus lebih dari 0 dan maksimal 360 derajat!");
        }
        this.sudutDerajat = sudutDerajat;
    }

    @Override
    public double hitungLuas() {
        return this.hitungLuasJuring();
    }

    @Override
    public double hitungKeliling() {
        return this.hitungKelilingJuring();
    }

    public double hitungLuasJuring() {
        this.luasJuring = (this.sudutDerajat / 360.0) * super.luas;
        return this.luasJuring;
    }

    public double hitungLuasJuring(double semiMayorLuar, double semiMinorLuar, double sudutDerajat) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Semi mayor luar dan semi minor luar juring elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Semi mayor luar tidak boleh lebih kecil dari semi minor luar!");
        }
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips harus lebih dari 0 dan maksimal 360 derajat!");
        }
        this.luasElips = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.luasJuring = (sudutDerajat / 360.0) * this.luasElips;
        return this.luasJuring;
    }

    public double hitungKelilingJuring() {
        this.panjangBusur = hitungPanjangBusurElips(super.semiMayorLuar, super.semiMinorLuar, this.sudutDerajat);
        if (Math.abs(this.sudutDerajat - 360.0) < 0.0000001) {
            this.sisiAwal = 0;
            this.sisiAkhir = 0;
        } else {
            this.sisiAwal = super.semiMayorLuar;
            this.sisiAkhir = hitungJarakPusatKeTitikElips(super.semiMayorLuar, super.semiMinorLuar, this.sudutDerajat);
        }
        this.rataRataJariJari = (this.sisiAwal + this.sisiAkhir) / 2.0;
        this.kelilingJuring = this.panjangBusur + this.sisiAwal + this.sisiAkhir;
        return this.kelilingJuring;
    }

    public double hitungKelilingJuring(double semiMayorLuar, double semiMinorLuar, double sudutDerajat) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Semi mayor luar dan semi minor luar juring elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Semi mayor luar tidak boleh lebih kecil dari semi minor luar!");
        }
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips harus lebih dari 0 dan maksimal 360 derajat!");
        }
        this.kelilingElips = super.hitungKeliling(semiMayorLuar, semiMinorLuar);
        this.panjangBusur = hitungPanjangBusurElips(semiMayorLuar, semiMinorLuar, sudutDerajat);
        if (Math.abs(sudutDerajat - 360.0) < 0.0000001) {
            this.sisiAwal = 0;
            this.sisiAkhir = 0;
        } else {
            this.sisiAwal = semiMayorLuar;
            this.sisiAkhir = hitungJarakPusatKeTitikElips(semiMayorLuar, semiMinorLuar, sudutDerajat);
        }
        this.rataRataJariJari = (this.sisiAwal + this.sisiAkhir) / 2.0;
        this.kelilingJuring = this.panjangBusur + this.sisiAwal + this.sisiAkhir;
        return this.kelilingJuring;
    }

    public double hitungPanjangBusurElips(double semiMayorLuar, double semiMinorLuar, double sudutDerajat) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Semi mayor luar dan semi minor luar juring elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Semi mayor luar tidak boleh lebih kecil dari semi minor luar!");
        }
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips harus lebih dari 0 dan maksimal 360 derajat!");
        }
        theta = Math.toRadians(sudutDerajat);
        jumlahSegmen = 1000;
        this.h = theta / jumlahSegmen;
        this.total = integrandBusur(semiMayorLuar, semiMinorLuar, 0) + integrandBusur(semiMayorLuar, semiMinorLuar, theta);

        for (int i = 1; i < jumlahSegmen; i++) {
            double t = i * this.h;
            this.total += (i % 2 == 0 ? 2 : 4) * integrandBusur(semiMayorLuar, semiMinorLuar, t);
        }

        panjangBusurElips = (this.h / 3.0) * this.total;
        return panjangBusurElips;
    }

    public double integrandBusur(double semiMayorLuar, double semiMinorLuar, double t) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Semi mayor luar dan semi minor luar juring elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Semi mayor luar tidak boleh lebih kecil dari semi minor luar!");
        }

        integrandBusur = Math.sqrt(Math.pow(-semiMayorLuar * Math.sin(t), 2) + Math.pow(semiMinorLuar * Math.cos(t), 2));

        return integrandBusur;
    }

    public double hitungJarakPusatKeTitikElips(double semiMayorLuar, double semiMinorLuar, double sudutDerajat) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Semi mayor luar dan semi minor luar juring elips harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Semi mayor luar tidak boleh lebih kecil dari semi minor luar!");
        }
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips harus lebih dari 0 dan maksimal 360 derajat!");
        }
        theta = Math.toRadians(sudutDerajat);
        jarakPusatKeTitikElips = Math.sqrt(
                Math.pow(semiMayorLuar * Math.cos(theta), 2)
                + Math.pow(semiMinorLuar * Math.sin(theta), 2)
        );
        return jarakPusatKeTitikElips;
    }

    @Override
    public void run() {
        if (this.isManual) {
            this.hitungLuasJuring(semiMayorLuar, semiMinorLuar, sudutDerajat);
            this.hitungKelilingJuring(semiMayorLuar, semiMinorLuar, sudutDerajat);
        } else {
            this.hitungLuas();
            this.hitungKeliling();
        }
    }
}
