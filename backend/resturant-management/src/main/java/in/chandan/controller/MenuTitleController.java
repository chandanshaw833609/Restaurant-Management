package in.chandan.controller;

import in.chandan.entity.MenuItems;
import in.chandan.entity.MenuTitle;
import in.chandan.repository.MenuTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class MenuTitleController {
    @Autowired
    private MenuTitleRepository menuTitleRepository;

    @PostMapping("/menu-titles")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addMenuTitle(@RequestBody MenuTitle menuTitle) {
        menuTitleRepository.save(menuTitle);
        return ResponseEntity.ok("Menu Title added successfully");
    }

    @GetMapping("/menu-titles")
//    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> getAllMenuTitle(){
        return ResponseEntity.ok(menuTitleRepository.findAll());
    }

    @GetMapping("/{title}/menu-items")
    public List<MenuItems> getAllMenuItems(@PathVariable String title){
        String title1 = title.replace("-", " ");
        MenuTitle menuTitle = menuTitleRepository.findByTitleContaining(title1);
        return menuTitle.getMenuItemsList();
    }
}