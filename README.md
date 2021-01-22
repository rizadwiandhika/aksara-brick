How to clone this repository :

Since this repository should be in form of Java Project, you cannot just clone this in usual way.

------------------------------------------------------------------------

Therefore to clone this repository properly, follow these steps:
1. Create java project folder (you can use Eclipse IDE or anything else up to your preference)
2. Initialize the folder with git
3. Add this remote to your local repository by running this command :

		git remote add origin https://github.com/rizadwiandhika/aksara-brick
	

4. Make sure the remote has been added to the local repository by running this command :
		
		git remote
		
	and the output shoul be : origin

5. Fetch all commits from remote to local

		git fetch origin main
		

6. Pull the commits to local

		git pull origin main
		
7. Then, don't forget to rename your branch 

		git branch -M main
		

------------------------------------------------------------------------

Contributor :

Muhammad Nevin		- 05111940000079 ( PBO - H )

Riza Dwi Andhika	- 05111940000149 ( PBO - H )
