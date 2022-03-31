package project.models;

public class DonDangKy {
    private int maDon;
    private String hoVaTen;
    private String namSinh;
    private String gioiTinh;
    private String quocTich;
    private String soCMND;
    private String loaiPhuongTien;
    private String soHieu;
    private String soGhe;
    private String ngayKhoiHanh;
    private String ngayKetThuc;
    private String thanhPhoDaDen;
    private String tinhThanh;
    private String quanHuyen;
    private String phuongXa;
    private String noiO;
    private String dienThoai;
    private String email;
    private boolean sot;
    private boolean buonNon;
    private boolean ho;
    private boolean tieuChay;
    private boolean khoTho;
    private boolean xuatHuyetNgoaiDa;
    private boolean dauHong;
    private boolean noiBanNgoaiDa;
    private boolean tiepXucNgoai;
    private boolean tiepXucNguoi;

    public DonDangKy() {
    }

    public DonDangKy(int maDon, String hoVaTen, String namSinh, String gioiTinh, String quocTich, String soCMND,
                     String loaiPhuongTien, String soHieu, String soGhe, String ngayKhoiHanh, String ngayKetThuc,
                     String thanhPhoDaDen, String tinhThanh, String quanHuyen, String phuongXa, String noiO,
                     String dienThoai, String email, boolean sot, boolean buonNon, boolean ho, boolean tieuChay,
                     boolean khoTho, boolean xuatHuyetNgoaiDa, boolean dauHong, boolean noiBanNgoaiDa, boolean tiepXucNgoai,
                     boolean tiepXucNguoi) {
        this.maDon = maDon;
        this.hoVaTen = hoVaTen;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.quocTich = quocTich;
        this.soCMND = soCMND;
        this.loaiPhuongTien = loaiPhuongTien;
        this.soHieu = soHieu;
        this.soGhe = soGhe;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.ngayKetThuc = ngayKetThuc;
        this.thanhPhoDaDen = thanhPhoDaDen;
        this.tinhThanh = tinhThanh;
        this.quanHuyen = quanHuyen;
        this.phuongXa = phuongXa;
        this.noiO = noiO;
        this.dienThoai = dienThoai;
        this.email = email;
        this.sot = sot;
        this.buonNon = buonNon;
        this.ho = ho;
        this.tieuChay = tieuChay;
        this.khoTho = khoTho;
        this.xuatHuyetNgoaiDa = xuatHuyetNgoaiDa;
        this.dauHong = dauHong;
        this.noiBanNgoaiDa = noiBanNgoaiDa;
        this.tiepXucNgoai = tiepXucNgoai;
        this.tiepXucNguoi = tiepXucNguoi;
    }

    public DonDangKy(String hoVaTen, String namSinh, String gioiTinh, String quocTich, String soCMND, String loaiPhuongTien,
                     String soHieu, String soGhe, String ngayKhoiHanh, String ngayKetThuc, String thanhPhoDaDen,
                     String tinhThanh, String quanHuyen, String phuongXa, String noiO, String dienThoai, String email,
                     boolean sot, boolean buonNon, boolean ho, boolean tieuChay, boolean khoTho, boolean xuatHuyetNgoaiDa,
                     boolean dauHong, boolean noiBanNgoaiDa, boolean tiepXucNgoai, boolean tiepXucNguoi) {
        this.hoVaTen = hoVaTen;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.quocTich = quocTich;
        this.soCMND = soCMND;
        this.loaiPhuongTien = loaiPhuongTien;
        this.soHieu = soHieu;
        this.soGhe = soGhe;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.ngayKetThuc = ngayKetThuc;
        this.thanhPhoDaDen = thanhPhoDaDen;
        this.tinhThanh = tinhThanh;
        this.quanHuyen = quanHuyen;
        this.phuongXa = phuongXa;
        this.noiO = noiO;
        this.dienThoai = dienThoai;
        this.email = email;
        this.sot = sot;
        this.buonNon = buonNon;
        this.ho = ho;
        this.tieuChay = tieuChay;
        this.khoTho = khoTho;
        this.xuatHuyetNgoaiDa = xuatHuyetNgoaiDa;
        this.dauHong = dauHong;
        this.noiBanNgoaiDa = noiBanNgoaiDa;
        this.tiepXucNgoai = tiepXucNgoai;
        this.tiepXucNguoi = tiepXucNguoi;
    }

    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getLoaiPhuongTien() {
        return loaiPhuongTien;
    }

    public void setLoaiPhuongTien(String loaiPhuongTien) {
        this.loaiPhuongTien = loaiPhuongTien;
    }

    public String getSoHieu() {
        return soHieu;
    }

    public void setSoHieu(String soHieu) {
        this.soHieu = soHieu;
    }

    public String getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(String soGhe) {
        this.soGhe = soGhe;
    }

    public String getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    public void setNgayKhoiHanh(String ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getThanhPhoDaDen() {
        return thanhPhoDaDen;
    }

    public void setThanhPhoDaDen(String thanhPhoDaDen) {
        this.thanhPhoDaDen = thanhPhoDaDen;
    }

    public String getTinhThanh() {
        return tinhThanh;
    }

    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    public String getNoiO() {
        return noiO;
    }

    public void setNoiO(String noiO) {
        this.noiO = noiO;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSot() {
        return sot;
    }

    public void setSot(boolean sot) {
        this.sot = sot;
    }

    public boolean isBuonNon() {
        return buonNon;
    }

    public void setBuonNon(boolean buonNon) {
        this.buonNon = buonNon;
    }

    public boolean isHo() {
        return ho;
    }

    public void setHo(boolean ho) {
        this.ho = ho;
    }

    public boolean isTieuChay() {
        return tieuChay;
    }

    public void setTieuChay(boolean tieuChay) {
        this.tieuChay = tieuChay;
    }

    public boolean isKhoTho() {
        return khoTho;
    }

    public void setKhoTho(boolean khoTho) {
        this.khoTho = khoTho;
    }

    public boolean isXuatHuyetNgoaiDa() {
        return xuatHuyetNgoaiDa;
    }

    public void setXuatHuyetNgoaiDa(boolean xuatHuyetNgoaiDa) {
        this.xuatHuyetNgoaiDa = xuatHuyetNgoaiDa;
    }

    public boolean isDauHong() {
        return dauHong;
    }

    public void setDauHong(boolean dauHong) {
        this.dauHong = dauHong;
    }

    public boolean isNoiBanNgoaiDa() {
        return noiBanNgoaiDa;
    }

    public void setNoiBanNgoaiDa(boolean noiBanNgoaiDa) {
        this.noiBanNgoaiDa = noiBanNgoaiDa;
    }

    public boolean isTiepXucNgoai() {
        return tiepXucNgoai;
    }

    public void setTiepXucNgoai(boolean tiepXucNgoai) {
        this.tiepXucNgoai = tiepXucNgoai;
    }

    public boolean isTiepXucNguoi() {
        return tiepXucNguoi;
    }

    public void setTiepXucNguoi(boolean tiepXucNguoi) {
        this.tiepXucNguoi = tiepXucNguoi;
    }
}
