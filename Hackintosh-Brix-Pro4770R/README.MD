# Brix Pro 4770R hakintosh 

This will help you install hakintosh (El Capitan) in Brix Pro 4770R. 

I am using 4gb/DDR3/1600Mhz with 500Gb 7200RPM hard drive. 

Note : Wifi will not work, so I am using LAN (you need to change default wifi device compatible with MACs) 

# installation

- Read installation from here : https://www.tonymacx86.com/threads/unibeast-install-os-x-el-capitan-on-any-supported-intel-based-pc.172672/

# Brix Pro Specific : 

(After Step 4) I am following Alternate steps 6

A. Download El Capitan Post-Install Tools (https://www.tonymacx86.com/resources/el-capitan-post-install-tools.294/) 

1. Install Clover_v2.3k_r3320-UEFI.pkg

2. Install CustoMac Essentials.pkg

B. Download Clover configurator : (https://www.tonymacx86.com/resources/clover-configurator.335/)
- From SMBIOS , use wizard to select select MacBook Pro Retina(11,2) - core i7 haswell
- You may save it 
- Reboot 

This will be make your hard drive rebootable. 

C. Download Multibeast 8.2.3 (https://www.tonymacx86.com/resources/multibeast-el-capitan-8-2-3.319/) and run it. I configured like 
- UEFI under quick start
- Drivers/Audio : ACL 269
- Drivers/Network : Realtek 8111v2.2.1
- Drivers/USB : Support for 7/8/9 series
- Boot loader UEFI 
- Customize/Definations/Mac Pro Ratina 11,2

![Config](multibeast.jpg)

And install it. It will take 2/3 min. And you are done. 


# Finally : Its running 

![System](system.jpg)

![Sound](sound.jpg)

![Display](GPU.jpg)

![HDD](hdd.jpg)
