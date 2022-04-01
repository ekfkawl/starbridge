package kr.starbridge.web.global.common.response;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ValidationGroups.NotEmptyGroup.class, ValidationGroups.SizekGroup.class, ValidationGroups.PatternGroup.class})
public interface ValidationSequence {
}