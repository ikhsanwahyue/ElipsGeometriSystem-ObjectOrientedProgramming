public class LimasElipsTerpancung extends LimasElips implements Runnable {

    public double faktorAtas, luasAtas, kelilingAtas, volumeTerpancung, luasPermukaanTerpancung, luasSelimutTerpancung;
    public double luasAlas, garisPelukisA, garisPelukisB, rataGariPelukis, rataGarisPelukis, selisihA, selisihB, kelilingAlas;

    public LimasElipsTerpancung(double semiMayorLuar, double semiMinorLuar, double tinggiLimas, double faktorAtas, boolean isManual) {
        super(semiMayorLuar, semiMinorLuar, tinggiLimas, isManual);
        if (faktorAtas <= 0 || faktorAtas >= 1) {
            throw new IllegalArgumentException("Faktor atas limas elips terpancung harus lebih dari 0 dan kurang dari 1!");
        }
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas elips terpancung harus lebih dari 0!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        this.faktorAtas = faktorAtas;
    }

    public double hitungVolumeTerpancung() {
        this.luasAtas = super.luas * Math.pow(this.faktorAtas, 2);
        this.volumeTerpancung = (this.tinggiLimas / 3.0) * (super.luas + this.luasAtas + Math.sqrt(super.luas * this.luasAtas));
        return this.volumeTerpancung;
    }

    public double hitungVolumeTerpancung(double semiMayorLuar, double semiMinorLuar, double tinggiLimas, double faktorAtas) {
        if (faktorAtas <= 0 || faktorAtas >= 1) {
            throw new IllegalArgumentException("Faktor atas limas elips terpancung harus lebih dari 0 dan kurang dari 1!");
        }
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas elips terpancung harus lebih dari 0!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        this.luasAlas = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.luasAtas = luasAlas * Math.pow(faktorAtas, 2);
        this.volumeTerpancung = (tinggiLimas / 3.0) * (luasAlas + this.luasAtas + Math.sqrt(luasAlas * this.luasAtas));
        return this.volumeTerpancung;
    }

    public double hitungLuasPermukaanTerpancung() {
        this.luasAtas = super.luas * Math.pow(this.faktorAtas, 2);
        this.kelilingAtas = super.keliling * this.faktorAtas;

        selisihA = super.semiMayorLuar - (super.semiMayorLuar * this.faktorAtas);
        selisihB = super.semiMinorLuar - (super.semiMinorLuar * this.faktorAtas);
        this.garisPelukisA = Math.sqrt(Math.pow(selisihA, 2) + Math.pow(this.tinggiLimas, 2));
        this.garisPelukisB = Math.sqrt(Math.pow(selisihB, 2) + Math.pow(this.tinggiLimas, 2));
        this.rataGarisPelukis = (garisPelukisA + garisPelukisB) / 2.0;

        this.luasSelimutTerpancung = 0.5 * (super.keliling + this.kelilingAtas) * rataGarisPelukis;
        this.luasPermukaanTerpancung = super.luas + this.luasAtas + this.luasSelimutTerpancung;
        return this.luasPermukaanTerpancung;
    }

    public double hitungLuasPermukaanTerpancung(double semiMayorLuar, double semiMinorLuar, double tinggiLimas, double faktorAtas) {
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas elips terpancung harus lebih dari 0!");
        }
        if (faktorAtas <= 0 || faktorAtas >= 1) {
            throw new IllegalArgumentException("Faktor atas limas elips terpancung harus lebih dari 0 dan kurang dari 1!");
        }
        if (semiMayorLuar <= 0 || semiMinorLuar <= 0) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor dan semi minor harus lebih besar dari 0!");
        }
        if (semiMayorLuar < semiMinorLuar) {
            throw new IllegalArgumentException("Panjang sumbu semi mayor harus lebih besar atau sama dengan sumbu semi minor!");
        }
        this.luasAlas = super.hitungLuas(semiMayorLuar, semiMinorLuar);
        this.kelilingAlas = super.hitungKeliling(semiMayorLuar, semiMinorLuar);
        this.luasAtas = luasAlas * Math.pow(faktorAtas, 2);
        this.kelilingAtas = kelilingAlas * faktorAtas;

        selisihA = semiMayorLuar - (semiMayorLuar * faktorAtas);
        selisihB = semiMinorLuar - (semiMinorLuar * faktorAtas);
        this.garisPelukisA = Math.sqrt(Math.pow(selisihA, 2) + Math.pow(tinggiLimas, 2));
        this.garisPelukisB = Math.sqrt(Math.pow(selisihB, 2) + Math.pow(tinggiLimas, 2));
        this.rataGarisPelukis = (garisPelukisA + garisPelukisB) / 2.0;

        this.luasSelimutTerpancung = 0.5 * (kelilingAlas + this.kelilingAtas) * rataGarisPelukis;
        this.luasPermukaanTerpancung = luasAlas + this.luasAtas + this.luasSelimutTerpancung;
        return this.luasPermukaanTerpancung;
    }

    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasPermukaanTerpancung(semiMayorLuar, semiMinorLuar, tinggiLimas, faktorAtas);
            this.hitungVolumeTerpancung(semiMayorLuar, semiMinorLuar, tinggiLimas, faktorAtas);
        } else {
            this.hitungLuasPermukaanTerpancung();
            this.hitungVolumeTerpancung();
        }
    }
}
