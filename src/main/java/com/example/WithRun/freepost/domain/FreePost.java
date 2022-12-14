package com.example.WithRun.freepost.domain;

import com.example.WithRun.freepost.dto.FreePostDTO;
import com.example.WithRun.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "freepost_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User freePostUser;

    @OneToMany(mappedBy = "freePostCommentFreePost")
    private List<FreePostComment> freePostCommentList = new ArrayList<>();

    @OneToOne(mappedBy = "freePostImageFreePost")
    private FreePostImage freePostImage;

    private String title;
    private String author;
    private String content;
    private LocalDateTime created_at;

    //==============================================================//

    public FreePostDTO toDTO(){
        return FreePostDTO.builder().id(id).title(title)
                .content(content).author(author)
                .created_at(created_at)
                .build();
    }

    public void addFreePostComment(FreePostComment freePostComment){
        freePostComment.setFreePostCommentFreePost(this);
        this.getFreePostCommentList().add(freePostComment);
    }


    public void addFreePostImage(FreePostImage freePostImage) {
        freePostImage.setFreePostImageFreePost(this);
        this.setFreePostImage(freePostImage);
    }
}
