package com.slee.web.common.controller.exbuilder;

import com.slee.web.common.model.common.MenuRequestDto;
import com.slee.web.common.service.CommonService;
import com.slee.web.constant.ResponseStatus;
import com.slee.web.api.model.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
public class CommonController {

    private final CommonService commonService;
    private final ResourceLoader resourceLoader;

    @Value("${file.path.apk}")
    private String apkFilePath;

    @PostMapping("/getMenu")
    @ApiIgnore
    public ResponseEntity<CommonResponse<Object>> getMenu(@RequestBody MenuRequestDto menuRequestDto) {
        CommonResponse<Object> commonResponseDto = CommonResponse.builder()
                .status(com.slee.web.constant.ResponseStatus.OK)
                .output(commonService.findAllByLnggDvCd(menuRequestDto.getLnggDvCd())).build();
        return ResponseEntity.ok(commonResponseDto);
    }

    @PostMapping("/getMenuByParent")
    @ApiIgnore
    public ResponseEntity<?> getMenuByParent(@RequestBody MenuRequestDto menuRequestDto) {
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .status(ResponseStatus.OK)
                .output(commonService.findByParent(menuRequestDto.getLnggDvCd(), menuRequestDto.getParent())).build();
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("/apk/download")
    @ApiIgnore
    public ResponseEntity<Object> download() {
        try {
            File file = new File(apkFilePath);
            InputStreamResource inputStreamResource = new InputStreamResource (new FileInputStream(file));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());
            return ResponseEntity.ok()
                    .headers(headers)
//                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(inputStreamResource);
        } catch (FileNotFoundException e) {
            log.error("There was an FileNotFoundException =>", e);
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e ) {
            log.error("There was an Exception =>", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
