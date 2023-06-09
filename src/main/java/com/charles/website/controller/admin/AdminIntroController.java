package com.charles.website.controller.admin;

import com.charles.website.model.MessageResponse;
import com.charles.website.services.IntroService;
import com.charles.website.utils.Authen;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/intro")
public class AdminIntroController {
    @Autowired
    private IntroService introService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam("link")String link) {
        Authen.check();

        introService.add(link);

        return ResponseEntity.ok(new MessageResponse("Create intro is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id")Long id,
                                    @Nullable @RequestParam("link")String link) {
        Authen.check();

        introService.update(id, link);

        return ResponseEntity.ok(new MessageResponse("Update intro is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id) {
        Authen.check();

        introService.delete(id);

        return ResponseEntity.ok(new MessageResponse("Delete info is success"));
    }
}
