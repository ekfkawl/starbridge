package kr.starbridge.web.global.aop;

import kr.starbridge.web.domain.bridge.controller.BridgeController;
import kr.starbridge.web.domain.bridge.enums.FunctionURIEnum;
import kr.starbridge.web.domain.etc.controller.InstallController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = {BridgeController.class, InstallController.class})
public class BridgeAdvice {

    /** Bridge URI Enum 모델에 추가 */
    @ModelAttribute
    public void addAttrBridgeFunctionURI(Model model) {
        for (FunctionURIEnum v : FunctionURIEnum.values()) {
            model.addAttribute(v.name(), "/bridge/" + v.getUri());
        }
    }
}
