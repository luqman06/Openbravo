nett <> gross
    nett: pajak ditanggung karyawan
    gross: pajak ditanggung perusahaan

rutin <> lain
rutin <> pendapatan tidak teratur
    rutin: untuk mensetahunkan cukup masa pph
    lain/tidak teratur: tidak disetahunkan


pajak rampung:
    1. terjadi di desember, atau
    2. terjadi di bulan karyawan pensiun, atau
    3. terjadi di bulan karyawan resign.

pajak tidak teratur saja:
    1. tidak ada pendapatan rutin
    2. hanya ada pendapatan lain/tidak teratur saja

pada entiti pph_pph21,
    jika ada kata-kata 'setahun': maka satuan amount atas field ini adalah 1 tahun
    jika TIDAK ada kata-kata 'setahun', atau ada kata-kata 'sebulan': maka satuan amount atas field ini adalah 1 bulan

bruto <> netto
    bruto: semua pendapatan kena pajak, baik gross maupun nett
    netto: bruto dikurangi pengurang pajak:
        biaya jabatan
        pengurang pajak
        ptkp


::field group::

employee information
    pyr sp employee
    employee
    bp tax marital status
    fiscal year
    month
    masa pph
    pajak rampung

pendapatan
    rutin sebulan gross
    rutin setahun gross
    rutin sebulan nett
    rutin setahun nett
    pendapatan lain nett
    pendapatan lain gross
    pendapatan bruto total setahun

pengurang pajak
    biaya jabatan setahun
    pengurang pajak setahun
    pkp setahun

pendapatan lain
    is pendapatan tidak teratur saja (Y/N)
    pph 21 bulan sebelumnya
    pendapatan rutin gross sebulan bulan lalu
    pendapatan rutin nett sebulan bulan lalu
    pendapatan lain gross bulan lalu
    pendapatan lain nett bulan lalu
    pengurang pajak bulan lalu
    pph 21 (dengan) bonus

hasil perhitungan pajak
    pph 21 dibayar [akumulasi pembayaran pph 21 selain pph_pph21 ini]
    pph 21 terhutang
    pph 21 terhutang bulan ini [pph 21 terhutang dikurangi pph 21 dibayar]
    pph 21 terpotong [sama dengan pph 21 terhutang bulan ini, kecuali tanpa NPWP, maka 20% lebih mahal]
    tunjangan pph 21 bulan ini
