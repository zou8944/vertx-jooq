package io.github.jklingsporn.vertx.jooq.generate.classic.async.guice;

import com.github.jasync.sql.db.mysql.exceptions.MySQLException;
import generated.classic.async.guice.Tables;
import generated.classic.async.guice.tables.daos.SomethingwithoutjsonDao;
import generated.classic.async.guice.tables.pojos.Somethingwithoutjson;
import io.github.jklingsporn.vertx.jooq.generate.AsyncDatabaseClientProvider;
import io.github.jklingsporn.vertx.jooq.generate.Credentials;
import io.github.jklingsporn.vertx.jooq.generate.MySQLConfigurationProvider;
import io.github.jklingsporn.vertx.jooq.generate.classic.ClassicTestBase;
import org.jooq.Condition;
import org.junit.Assert;
import org.junit.BeforeClass;

import java.util.Random;

/**
 * Created by jensklingsporn on 02.11.16.
 */
public class SomethingWithoutJsonDaoTest extends ClassicTestBase<Somethingwithoutjson, Integer, String, SomethingwithoutjsonDao> {

    public SomethingWithoutJsonDaoTest() {
        super(Tables.SOMETHINGWITHOUTJSON.SOMESTRING, new SomethingwithoutjsonDao(MySQLConfigurationProvider.getInstance().createDAOConfiguration(), AsyncDatabaseClientProvider.getInstance().getClient(Credentials.MYSQL)));
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        MySQLConfigurationProvider.getInstance().setupDatabase();
    }

    @Override
    protected Somethingwithoutjson create() {
        return createWithId().setSomeid(null);
    }

    @Override
    protected Somethingwithoutjson createWithId() {
        Random random = new Random();
        Somethingwithoutjson something = new Somethingwithoutjson();
        something.setSomeid(random.nextInt());
        something.setSomestring("my_string " + random.nextInt());
        return something;
    }

    @Override
    protected Somethingwithoutjson setId(Somethingwithoutjson pojo, Integer id) {
        return pojo.setSomeid(id);
    }

    @Override
    protected Somethingwithoutjson setSomeO(Somethingwithoutjson pojo, String someO) {
        return pojo.setSomestring(someO);
    }

    @Override
    protected Integer getId(Somethingwithoutjson pojo) {
        return pojo.getSomeid();
    }

    @Override
    protected String createSomeO() {
        return "asdf";
    }

    @Override
    protected Condition eqPrimaryKey(Integer id) {
        return Tables.SOMETHINGWITHOUTJSON.SOMEID.eq(id);
    }

    @Override
    protected void assertDuplicateKeyException(Throwable x) {
        Assert.assertEquals(MySQLException.class, x.getClass());
    }
}
