// INHERITENCE : JuringElips3D (3D) turunan dari BolaElips (3D).
// MULTHITREADING : "implements Runnable" agar bisa berjalan sebagai Thread mandiri untuk masing-masing bangun ruang.   
public class JuringElips3D extends BolaElips implements Runnable {

    // ENCAPSULATION : Atribut state untuk membungkus hasil perhitungan volume dan luas permukaan juring elips 3D.
    public double sudutDerajat, tinggiJuring, luasAlasJuring, kelilingAlasJuring, volumeJuringElips3D, luasPermukaanJuringElips3D;
    public double luasElips, panjangBusur, rataRataJariJari, kelilingElips;
    public double sisiAwal, sisiAkhir, theta, integrandBusur, jarakPusatKeTitikElips, h, total, jumlahSegmen;

    // Constructor untuk menginisialisasi atribut state juring elips 3D.
    public JuringElips3D(double semiMayorLuar, double semiMinorLuar, double tinggiJuring, double sudutDerajat, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, tinggiJuring, isManual);

        // Validasi : Memastikan nilai semi mayor dan semi minor lebih besar dari 0, serta semi mayor lebih besar atau sama dengan semi minor, tinggi juring lebih besar dari 0, dan sudut derajat lebih dari 0 dan maksimal 360.
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        if (tinggiJuring <= 0) {
            throw new IllegalArgumentException("Tinggi juring elips 3D harus lebih besar dari 0!");
        }
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips 3D harus lebih dari 0 dan maksimal 360 derajat!");
        }
        this.tinggiJuring = tinggiJuring;
        this.sudutDerajat = sudutDerajat;
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------
    // OVERRIDING : Implementasi method hitungVolume() dan hitungLuasPermukaan() dari interface BendaGeometri.

    public double hitungVolumeJuringElips3D() {
        this.luasAlasJuring = (this.sudutDerajat / 360.0) * super.luas;
        this.volumeJuringElips3D = this.luasAlasJuring * this.tinggiJuring;
        return this.volumeJuringElips3D;
    }

    // OVERRIDING : Implementasi method hitungLuasPermukaan() dari interface BendaGeometri.
    public double hitungVolumeJuringElips3D(double semiMayorLuar, double semiMinorLuar, double tinggiJuring, double sudutDerajat) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        if (tinggiJuring <= 0) {
            throw new IllegalArgumentException("Tinggi juring elips 3D harus lebih besar dari 0!");
        }
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips 3D harus lebih dari 0 dan maksimal 360 derajat!");
        }
        luasElips = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.luasAlasJuring = (sudutDerajat / 360.0) * luasElips;
        this.volumeJuringElips3D = this.luasAlasJuring * tinggiJuring;
        return this.volumeJuringElips3D;
    }

    // OVERRIDING : Implementasi method hitungLuasPermukaan() dari interface BendaGeometri.
    public double hitungLuasPermukaanJuringElips3D() {
        panjangBusur = hitungPanjangBusurElips(super.semiMayorLuar, super.semiMinorLuar, this.sudutDerajat);
        if (Math.abs(this.sudutDerajat - 360.0) < 0.0000001) {
            sisiAwal = 0;
            sisiAkhir = 0;
        } else {
            sisiAwal = super.semiMayorLuar;
            sisiAkhir = hitungJarakPusatKeTitikElips(super.semiMayorLuar, super.semiMinorLuar, this.sudutDerajat);
        }
        rataRataJariJari = (sisiAwal + sisiAkhir) / 2.0;
        this.kelilingAlasJuring = panjangBusur + sisiAwal + sisiAkhir;
        this.luasAlasJuring = (this.sudutDerajat / 360.0) * super.luas;
        this.luasPermukaanJuringElips3D = (2 * this.luasAlasJuring) + (this.kelilingAlasJuring * this.tinggiJuring);
        return this.luasPermukaanJuringElips3D;
    }

    // OVERRIDING : Implementasi method hitungLuasPermukaan() dari interface BendaGeometri.
    public double hitungLuasPermukaanJuringElips3D(double semiMayorLuar, double semiMinorLuar, double tinggiJuring, double sudutDerajat) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        if (tinggiJuring <= 0) {
            throw new IllegalArgumentException("Tinggi juring elips 3D harus lebih besar dari 0!");
        }
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips 3D harus lebih dari 0 dan maksimal 360 derajat!");
        }
        luasElips = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        kelilingElips = super.hitungKeliling(semiMayorLuar, semiMinorLuar);
        panjangBusur = hitungPanjangBusurElips(semiMayorLuar, semiMinorLuar, sudutDerajat);
        if (Math.abs(sudutDerajat - 360.0) < 0.0000001) {
            sisiAwal = 0;
            sisiAkhir = 0;
        } else {
            sisiAwal = semiMayorLuar;
            sisiAkhir = hitungJarakPusatKeTitikElips(semiMayorLuar, semiMinorLuar, sudutDerajat);
        }
        rataRataJariJari = (sisiAwal + sisiAkhir) / 2.0;
        this.kelilingAlasJuring = panjangBusur + sisiAwal + sisiAkhir;
        this.luasAlasJuring = (sudutDerajat / 360.0) * luasElips;
        this.luasPermukaanJuringElips3D = (2 * this.luasAlasJuring) + (this.kelilingAlasJuring * tinggiJuring);
        return this.luasPermukaanJuringElips3D;
    }

    // OVERRIDING : method run() milik Runnable untuk menjalankan perhitungan luas dan keliling juring elips secara otomatis saat thread dijalankan.
    public double hitungPanjangBusurElips(double semiMayorLuar, double semiMinorLuar, double sudutDerajat) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        if (tinggiJuring <= 0) {
            throw new IllegalArgumentException("Tinggi juring elips 3D harus lebih besar dari 0!");
        }
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips 3D harus lebih dari 0 dan maksimal 360 derajat!");
        }
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

    // OVERRIDING : method run() milik Runnable untuk menjalankan perhitungan luas dan keliling juring elips secara otomatis saat thread dijalankan.
    public double integrandBusur(double semiMayorLuar, double semiMinorLuar, double t) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        if (tinggiJuring <= 0) {
            throw new IllegalArgumentException("Tinggi juring elips 3D harus lebih besar dari 0!");
        }
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips 3D harus lebih dari 0 dan maksimal 360 derajat!");
        }

        integrandBusur = Math.sqrt(Math.pow(-semiMayorLuar * Math.sin(t), 2) + Math.pow(semiMinorLuar * Math.cos(t), 2));
        return integrandBusur;
    }

    // OVERRIDING : method run() milik Runnable untuk menjalankan perhitungan luas dan keliling juring elips secara otomatis saat thread dijalankan.
    public double hitungJarakPusatKeTitikElips(double semiMayorLuar, double semiMinorLuar, double sudutDerajat) {
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        if (tinggiJuring <= 0) {
            throw new IllegalArgumentException("Tinggi juring elips 3D harus lebih besar dari 0!");
        }
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips 3D harus lebih dari 0 dan maksimal 360 derajat!");
        }
        theta = Math.toRadians(sudutDerajat);

        jarakPusatKeTitikElips = Math.sqrt(
                Math.pow(semiMayorLuar * Math.cos(theta), 2)
                + Math.pow(semiMinorLuar * Math.sin(theta), 2)
        );
        return jarakPusatKeTitikElips;
    }

    // OVERRIDING : method run() milik Runnable untuk menjalankan perhitungan luas dan keliling juring elips secara otomatis saat thread dijalankan.
    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasPermukaanJuringElips3D(semiMayorLuar, semiMinorLuar, tinggiJuring, sudutDerajat);
            this.hitungVolumeJuringElips3D(semiMayorLuar, semiMinorLuar, tinggiJuring, sudutDerajat);
        } else {
            this.hitungLuasPermukaanJuringElips3D();
            this.hitungVolumeJuringElips3D();
        }
    }
}
