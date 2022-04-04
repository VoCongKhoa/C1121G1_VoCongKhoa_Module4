package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.models.Song;
import project.models.SongForm;
import project.services.ISongService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@RequestMapping(value = "/song")
@Controller
public class SongController {

    // Singleton
    @Autowired
    private ISongService iSongService;

    @GetMapping(value = {"/", "/list"})
    public ModelAndView goListSong() {
        ModelAndView modelAndView = new ModelAndView("list_song");
        List<Song> songList = iSongService.findAll();
//        modelAndView.addObject("myFile", new MyFile());
        modelAndView.addObject("songList", songList);

        return modelAndView;
    }

    @GetMapping(value = "/detail")
    public String goDetailSong(@RequestParam Integer idSong, Model model) {
        model.addAttribute("songObj",
                this.iSongService.findById(idSong));

        return "detail_song";
    }

    @GetMapping(value = "/detail/{idSong}")
    public String goDetailSongPV(@PathVariable Integer idSong, Model model) {
        model.addAttribute("songObj",
                this.iSongService.findById(idSong));

        return "detail_song";
    }

    @GetMapping(value = "/create")
    public String goCreateNewSong(Model model) {
        model.addAttribute("song", new Song());
        model.addAttribute("songForm", new SongForm());
        return "create_song";
    }

    @PostMapping(value = "/create")
    public String createSong(@ModelAttribute("songForm") SongForm songForm, RedirectAttributes redirectAttributes) {
        Song song = new Song(songForm.getId(), songForm.getName(), songForm.getSinger(), songForm.getCategory(),
                songForm.getMultipartFile().getOriginalFilename());
        this.iSongService.save(song);
        redirectAttributes.addFlashAttribute("msg",
                "Create new successfully!");

        try {
            MultipartFile multipartFile = songForm.getMultipartFile();
            String fileName = multipartFile.getOriginalFilename();
            assert fileName != null;
            File file = new File("D:/files", fileName);
            multipartFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addAttribute("message", "upload failed");
        }

        return "redirect:/song/list";
    }

    @GetMapping(value = "/update")
    public String goUpdate(@RequestParam int idSong, Model model) {
        Song song = iSongService.findById(idSong);
//        System.out.println(song);
        model.addAttribute("songObj", song);
        return "update_song";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute Song song) {
        iSongService.update(song);
        return "redirect:/song/list";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam int idDelete, RedirectAttributes redirectAttributes) {
        iSongService.delete(idDelete);
        redirectAttributes.addFlashAttribute("msgDelete", "Delete song successfully!");
        return "redirect:/song/list";
    }

    @GetMapping(value = "/search")
    public String searchByName(@RequestParam String nameSearch, Model model) {
        List<Song> songList = new ArrayList<>();
        songList = iSongService.searchByName(nameSearch);
        model.addAttribute("songList", songList);
        return "list_song";
    }

//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    public String uploadFile(MyFile myFile, Model model) {
//        model.addAttribute("message", "upload success");
//        model.addAttribute("description", myFile.getDescription());
//
//
//        return "result";
//    }


}
