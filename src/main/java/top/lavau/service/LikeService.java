package top.lavau.service;

public interface LikeService {
    void like(Boolean isLike, String id);

    Integer getLikeNumByStuId(String stuId);
}
