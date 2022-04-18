package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.KhachHang;
import project.repositories.IKhachHangRepository;

import java.util.List;

@Service
public class KhachHangService implements IKhachHangService{
    @Autowired
    IKhachHangRepository iKhachHangRepository;

    @Override
    public List<KhachHang> findAll() {
        return iKhachHangRepository.findAll();
    }
}
