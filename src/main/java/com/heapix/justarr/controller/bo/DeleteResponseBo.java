package com.heapix.justarr.controller.bo;

/**
 * @author mgergalov
 */
public class DeleteResponseBo {

    private Long id;

    public DeleteResponseBo(Long id) {
        this.id = id;
    }

    public DeleteResponseBo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
