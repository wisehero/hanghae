package com.wisehero.myselectshop.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/api")
class ShopController {

    @GetMapping("/shop")
    fun shop(): ModelAndView {
        return ModelAndView("index")
    }
}