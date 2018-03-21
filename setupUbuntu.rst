==============================================
Setting up Ubuntu Machine for running the game
==============================================

Install Maven
--------------

*    Run the following commands on your Ubuntu Machine. Refer your distribution manual to install the packages on your computer.

::

	$ sudo apt install maven
	Reading package lists... Done
	Building dependency tree       
	Reading state information... Done
	The following additional packages will be installed:
	>>>>>>>>>> Snipped <<<<<<<<<<<<<
	0 added, 0 removed; done.
	Running hooks in /etc/ca-certificates/update.d...

	done.
	done.

Install Openjdk
---------------

*    Run the following commands on your Ubuntu Machine. Refer your distribution's manual to install the packages on your computer.

* Add ppa repo.

::

	$ sudo add-apt-repository ppa:openjdk-r/ppa



	More info: https://launchpad.net/~openjdk-r/+archive/ubuntu/ppa
	Press [ENTER] to continue or ctrl-c to cancel adding it

	gpg: keyring `/tmp/tmp39o8t7cq/secring.gpg' created
	gpg: keyring `/tmp/tmp39o8t7cq/pubring.gpg' created
	gpg: requesting key 86F44E2A from hkp server keyserver.ubuntu.com
	gpg: /tmp/tmp39o8t7cq/trustdb.gpg: trustdb created
	gpg: key 86F44E2A: public key "Launchpad OpenJDK builds (all archs)" imported
	gpg: Total number processed: 1
	gpg:               imported: 1  (RSA: 1)
	OK
		
	>>>>>>>>>> Snipped <<<<<<<<<<<<<

* Update the repo.

::

	$ sudo apt-get -y update 

	Hit:1 http://ppa.launchpad.net/openjdk-r/ppa/ubuntu xenial InRelease
	Hit:2 http://security.ubuntu.com/ubuntu xenial-security InRelease
	Hit:3 http://in.archive.ubuntu.com/ubuntu xenial InRelease

	
	>>>>>>>>>> Snipped <<<<<<<<<<<<<

* Install JDK

::

	$ sudo apt-get install -y openjdk-8-jdk


	Reading package lists... Done
	Building dependency tree       
	Reading state information... Done
	The following packages were automatically installed and are no longer required:
	  gnome-software-common libgtkspell3-3-0
	Use 'sudo apt autoremove' to remove them.
	The following additional packages will be installed:

	
	>>>>>>>>>> Snipped <<<<<<<<<<<<<

Install GraphViz
---------------

*    Run the following commands to install GraphViz. It is needed to generate the diagrams in javadoc.


::

	$ sudo apt-get install graphviz
	
	Reading package lists... Done
	Building dependency tree
	
	>>>>>>>>>> Snipped <<<<<<<<<<<<<
	
	Setting up graphviz (2.38.0-12ubuntu2.1) ...
