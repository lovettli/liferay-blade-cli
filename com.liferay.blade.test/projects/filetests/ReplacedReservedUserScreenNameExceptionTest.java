package blade.migrate.liferay70;

public class ChangesUserServicesThrownExceptionsTest {

	public static void main(String[] args) {

		try
		{
			System.out.println("exception");
		}
		catch(ReservedUserScreenNameException e)
		{
		}
	}

}
