package quite.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import quite.entity.Books;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: pual
 * Date: 2015/10/26
 * Desc:
 */
public interface BookService extends PagingAndSortingRepository<Books,Integer> {
    Optional<Books> findBookByName(String name);

    @Query("select b from Books b where name=? or price like ?")
    Optional<List<Books>> findBooks(String name, double price);
    @Query("select b from Books b where name like concat('%',?1,'%')")
    Page<Books> find(String name, Pageable pageable);
}
