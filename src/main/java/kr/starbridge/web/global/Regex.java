package kr.starbridge.web.global;

public class Regex {
    /** 공백 제거 */
    public final static String VACUUM = "\\p{Z}";
    /** 배틀태그 양식 */
    public final static String BATTLE_TAG = "[0-9ㄱ-ㅎ가-힣A-Za-z]{0,32}\\#[0-9]{3,8}";
    /** MD5 해시 양식 */
    public final static String MD5_HASH = "^[a-zA-Z0-9]{32,32}$";
    /** 앞 뒤 공백 제거 */
    public final static String TRIM = "(^\\p{Z}+|\\p{Z}+$)";
    /** 이미지 파일 확장자 */
    public final static String IS_IMAGE = "[^\\s]+(.*?)\\.(jpg|jpeg|png|gif|bmp)$";
}
