package com.iss.ua.lark.common.core.domain;

/**
 * @author JunoSong
 * @version V1.0
 * @Package com.iss.ua.lark.common.core.domain
 * @date 2023/3/1 13:59
 */
public class PageQuery<T> {
    private Long pageNum = 1L;
    private Long pageSize = 20L;
    private T param;

    public PageQuery() {
    }

    public Long getPageNum() {
        return this.pageNum;
    }

    public Long getPageSize() {
        return this.pageSize;
    }

    public T getParam() {
        return this.param;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageQuery)) {
            return false;
        } else {
            PageQuery<?> other = (PageQuery)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$pageNum = this.getPageNum();
                    Object other$pageNum = other.getPageNum();
                    if (this$pageNum == null) {
                        if (other$pageNum == null) {
                            break label47;
                        }
                    } else if (this$pageNum.equals(other$pageNum)) {
                        break label47;
                    }

                    return false;
                }

                Object this$pageSize = this.getPageSize();
                Object other$pageSize = other.getPageSize();
                if (this$pageSize == null) {
                    if (other$pageSize != null) {
                        return false;
                    }
                } else if (!this$pageSize.equals(other$pageSize)) {
                    return false;
                }

                Object this$param = this.getParam();
                Object other$param = other.getParam();
                if (this$param == null) {
                    if (other$param != null) {
                        return false;
                    }
                } else if (!this$param.equals(other$param)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageQuery;
    }

    public int hashCode() {
        int result = 1;
        Object $pageNum = this.getPageNum();
        result = result * 59 + ($pageNum == null ? 43 : $pageNum.hashCode());
        Object $pageSize = this.getPageSize();
        result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        Object $param = this.getParam();
        result = result * 59 + ($param == null ? 43 : $param.hashCode());
        return result;
    }

    public String toString() {
        return "PageQuery(pageNum=" + this.getPageNum() + ", pageSize=" + this.getPageSize() + ", param=" + this.getParam() + ")";
    }
}