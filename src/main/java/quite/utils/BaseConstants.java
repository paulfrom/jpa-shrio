/**
 *   上海华盖科技发展有限公司
 *   FILENAME     :  BaseConstants.java
 *   PACKAGE      :  com.hg.core.constant
 *   CREATE DATE  :  2014�?3�?20�?
 *   MODIFIED BY  :
 *	 DESCRIPTION  :  
 */

package quite.utils;

import org.springframework.web.context.WebApplicationContext;

/**
 * @�ļ����� ϵͳ����������
 * @version CVN #V1# #2014��3��20��#
 */
public class BaseConstants {

	/** ��¼�û���Ӧ��Session Info */
	public static final String USER_SESSION_INFO = "UserSessionInfo";

	/** ���ڸ�ʽ */
	public static final String DATE_PATTERN_CN = "yyyy��MM��dd��";

	/** ���ڸ�ʽ ** */
	public final static String DATE_PATTERN = "yyyy-MM-dd";

	/** ���ڸ�ʽ �����������ڸ�ʽ��Ŀ¼���� */
	public static final String DATE_PATTERN_2 = "yyyyMMdd";

	/** ���ڸ�ʽ ** */
	public final static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/** ���ָ�ʽ1 */
	public static final String NUMBER_PATTERN_3 = "#.##";

	/** ����ģʽ. */
	public static boolean DEVELOP_MODE = false;

	/** ÿҳ�ļ�¼��. */
	public final static int PAGE_SIZE = 20;

	/** PDF��ӡ��ʽ */
	public final static String PRINT_PDF = "pdf";

	/** XLS��ӡ��ʽ */
	public final static String PRINT_XLS = "xls";

	/** ������Ϣ���ݱ�����session��key ** */
	public final static String ERROR_MSG_SESSION = "ERROR_MSG_SESSION";

	/** ��֤������Ϣ���ݱ�����session��key ** */
	public final static String FIELD_ERROR_SESSION = "FIELD_ERROR_SESSION";

	/** ��ʾ��Ϣ���ݱ�����session��key ** */
	public final static String HINT_MSG_SESSION = "HINT_MSG_SESSION";

	/** ÿ�ű�Ĺ����ֶΣ�״̬��־ 1����Ч��. */
	public final static Integer ACTIVE_FLAG = 1;
	/** ÿ�ű�Ĺ����ֶΣ�״̬��־ 0����Ч��. */
	public final static Integer DACTIVE_FLAG = 0;

	/** Ӧ�ó���ϵͳ������Դ�ļ�. */
	public static String APPLICATION_RESOURCE_FILE = "application";

	/** ��Դ�ļ�Ĭ��ֵ. */
	public static String APPLICATION_DEFAULT_VALUE = "";

	/** ϵͳ������·��. */
	public static String APPLICATION_CONTEXT_PATH = null;

	/** ϵͳ�ֵ�� */
	public final static String APPLICATION_SYS_CODE = "syscode";
	/** ϵͳ�ֵ������ֶ� */
	public final static String APPLICATION_SYS_CODE_NAME = "codeType";

	/** ϵͳURL·��*/
	public static String APPLICATION_URL_PATH = null;

	/** ÿ�μ���head.jsp�������ļ����� */
	public static String DEVELOP_MODE_INITHEAD_NAME = "application_devMode_initHead";

	/** ����ģʽ�������ļ����� */
	public static String DEVELOP_MODE_NAME = "application_devMode";

	/** Ӧ�ó����ҳ��ý���ļ�����. */
	public static String APPLICATION_META = null;

	/** ������·���Ĳ������� */
	public static String APPLICATION_CONTEXT_PATH_NAME = "ctxPath";

	/** ͼƬ�滻�ؼ���. */
	public static String PREDEFINE_KEY_IMG = "#\\{ctx\\}";

	/** Spring�����Ķ���. */
	public static WebApplicationContext SPRING_APPLICATION_CONTEXT = null;
	/** ϵͳ�ָ�� */
	public static final String SYSTEM_SEPARATOR = "/";
	/** ҳ�湤���������� */
	public final static String WEB_UTILS_NAME = "utils";

	public final static String WEB_CACHE_NAME = "cache";

	/** �ϴ��ļ����·������Ҫ����Ӧ�÷���������xml�ļ��ﶨ���������·������һ�� * */
	public static final String UPLOAD_DIR = "/uploadFiles";
	public static final String UPLOAD_DIR_1 = "/uploadFiles/upload";

	/**ϵͳ�����ļ�����*/
	public static final String SYSTEM_PROP_FILE = "ds.properties";

	/**�ļ��洢Ŀ¼��������*/
	public static final String SYSTEM_FILE_DIR = "file.dir";

	/**��������С*/
	public static final int BUFFER_SIZE = 16 * 1024;

	/** ���ָ�ʽ0 */
	public static final String NUMBER_PATTERN = "###0.00";

	/** ���ָ�ʽ1 */
	public static final String NUMBER_PATTERN_1 = "0.00";
	
	public static final String DISCRIMINATOR = "00000000000";

}
