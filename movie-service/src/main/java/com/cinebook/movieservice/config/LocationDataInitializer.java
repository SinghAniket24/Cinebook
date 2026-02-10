package com.cinebook.movieservice.config;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cinebook.movieservice.entity.Cinema;
import com.cinebook.movieservice.entity.City;
import com.cinebook.movieservice.repository.CinemaRepository;
import com.cinebook.movieservice.repository.CityRepository;

@Component
public class LocationDataInitializer implements CommandLineRunner {

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private CinemaRepository cinemaRepo;

    @Override
    @Transactional
    public void run(String... args) {
        System.out.println("🇮🇳 Seeding ALL India Data (GPS Enabled)...");

        // 1. Create Cities (Safe Check)
        createCitySafe("Mumbai", "Gateway of India Icon");
        createCitySafe("Delhi-NCR", "India Gate Icon");
        createCitySafe("Bengaluru", "Vidhana Soudha Icon");
        createCitySafe("Hyderabad", "Charminar Icon");
        createCitySafe("Ahmedabad", "Teen Darwaza Icon");
        createCitySafe("Chennai", "Temple Icon");
        createCitySafe("Kolkata", "Victoria Memorial Icon");
        createCitySafe("Pune", "Shaniwar Wada Icon");
        createCitySafe("Chandigarh", "Open Hand Icon");
        createCitySafe("Kochi", "Chinese Fishing Net Icon");
        // 2. Populate Mumbai Cinemas (FULL LIST FROM SCREENSHOTS)
     // 4. Populate Mumbai Cinemas (With GPS Coordinates)
     // 2. Populate Mumbai
        seedCinemas("Mumbai", Arrays.asList(
            // SOUTH MUMBAI & CENTRAL
            new Object[]{"Sterling Cineplex", "Fort, Azad Maidan", 18.9366, 72.8329},
            new Object[]{"Regal Cinema", "Colaba, Apollo Bunder", 18.9234, 72.8324},
            new Object[]{"EROS INOX IMAX", "Churchgate", 18.9329, 72.8286},
            new Object[]{"Mukta A2: New Excelsior", "Fort, Near VT Station", 18.9372, 72.8335},
            new Object[]{"INOX Laserplex: CR2", "Nariman Point", 18.9272, 72.8236},
            new Object[]{"Metro INOX Cinemas", "Marine Lines", 18.9431, 72.8277},
            new Object[]{"Maratha Mandir", "Mumbai Central", 18.9717, 72.8214},
            new Object[]{"Nishat Cinema", "Grant Road", 18.9610, 72.8188},
            new Object[]{"Chitra Cinema", "Dadar (East)", 19.0152, 72.8465},
            new Object[]{"Plaza Cinema", "Dadar (West)", 19.0195, 72.8407},
            new Object[]{"Gold Cinema: Dadar", "Dadar (East)", 19.0163, 72.8450},
            new Object[]{"INOX: Nakshatra Mall", "Dadar (West)", 19.0205, 72.8415},
            new Object[]{"Citylight Cinema", "Mahim", 19.0346, 72.8427},
            new Object[]{"Movietime Star City", "Matunga (West)", 19.0294, 72.8458},
            new Object[]{"PVR ICON: Phoenix Palladium", "Lower Parel", 18.9953, 72.8245},
            new Object[]{"Mukta A2: Jai Hind", "Lalbaugh", 18.9987, 72.8398},
            new Object[]{"MovieMax: Sion", "Sion Circle", 19.0401, 72.8617},
            new Object[]{"Miraj Cinemas: IMAX", "Wadala", 19.0267, 72.8778},

            // BANDRA TO ANDHERI
            new Object[]{"G7 Multiplex", "Bandra (West)", 19.0596, 72.8295},
            new Object[]{"PVR: Le Reve-Globus Mall", "Bandra (West)", 19.0558, 72.8335},
            new Object[]{"Movietime Suburbia", "Bandra (West)", 19.0620, 72.8350},
            new Object[]{"Mukta A2: Orion", "Santacruz (East)", 19.0790, 72.8480},
            new Object[]{"Gold Cinema: Santacruz", "Santacruz (West)", 19.0837, 72.8385},
            new Object[]{"PVR: Lido", "Juhu, Santa Cruz (W)", 19.0913, 72.8298},
            new Object[]{"PVR: Dynamix Mall", "Juhu", 19.1128, 72.8286},
            new Object[]{"Maison INOX: Jio World Plaza", "BKC", 19.0664, 72.8647},
            new Object[]{"Maison PVR: Jio World Drive", "BKC", 19.0631, 72.8550},
            new Object[]{"Bahar Cinema", "Vile Parle (East)", 19.0984, 72.8519},
            new Object[]{"Mukta A2: SunCity", "Vile Parle (East)", 19.1025, 72.8530},
            new Object[]{"PVR ICON: Infiniti", "Andheri (West)", 19.1408, 72.8317},
            new Object[]{"PVR: Citi Mall", "Andheri (West)", 19.1412, 72.8295},
            new Object[]{"PVR: C&B Square Chakala", "Andheri (East)", 19.1126, 72.8610},
            new Object[]{"MovieMax: Andheri", "Andheri-Kurla Road", 19.1147, 72.8687},
            new Object[]{"Fun Republic: Cinepolis", "Andheri (West)", 19.1328, 72.8322},

            // MALAD TO BORIVALI
            new Object[]{"PVR: Infiniti", "Malad (West)", 19.1843, 72.8348},
            new Object[]{"INOX: Megaplex Inorbit", "Malad (West)", 19.1728, 72.8353},
            new Object[]{"MOVIETIME: Malad", "Malad (West)", 19.1865, 72.8375},
            new Object[]{"Kasturba Cinema", "Malad (West)", 19.1892, 72.8425},
            new Object[]{"Gold Cinema: Malad", "Malad", 19.1830, 72.8450},
            new Object[]{"Topiwala Mukta A2", "Goregaon (West)", 19.1672, 72.8420},
            new Object[]{"HDFC Millennia PVR ICON", "Oberoi Mall, Goregaon", 19.1741, 72.8604},
            new Object[]{"MOVIE TIME: HUB", "Goregaon (East)", 19.1558, 72.8535},
            new Object[]{"Miraj Cinemas: Anupam", "Goregaon (East)", 19.1630, 72.8570},
            new Object[]{"INOX: Raghuleela Mall", "Kandivali (West)", 19.2104, 72.8385},
            new Object[]{"PVR: Milap", "Kandivali (West)", 19.2085, 72.8420},
            new Object[]{"Maxus Cinemas", "Kandivali (East)", 19.2052, 72.8660},
            new Object[]{"INOX Megaplex: Sky City", "Borivali (East)", 19.2275, 72.8590},
            new Object[]{"Ajanta Cinema Cinex", "Borivali (West)", 19.2258, 72.8520},
            new Object[]{"Gold Cinema: Sona", "Borivali (East)", 19.2290, 72.8640},
            new Object[]{"Maxus Cinemas", "Borivali (West)", 19.2305, 72.8490},
            new Object[]{"Movietime: Dahisar", "Dahisar (East)", 19.2555, 72.8645},
            new Object[]{"INOX: Thakur Mall", "Dahisar", 19.2608, 72.8710},

            // THANE & BEYOND
            new Object[]{"Cinepolis: Lake Shore", "Thane (Viviana Mall)", 19.2094, 72.9715},
            new Object[]{"Cinepolis: VIP Lake Shore", "Thane (Viviana Mall)", 19.2094, 72.9715},
            new Object[]{"INOX: Korum Mall", "Thane (West)", 19.2014, 72.9688},
            new Object[]{"Anand Cinema", "Thane (East)", 19.1960, 72.9805},
            new Object[]{"Gold Cinema: Shivaji Road", "Thane (West)", 19.1915, 72.9710},
            new Object[]{"MovieMax: Wonder Mall", "Thane", 19.2190, 72.9730},
            new Object[]{"MovieMax: Eternity Mall", "Thane", 19.1865, 72.9610},
            new Object[]{"Cinepolis: High Street", "Thane", 19.2205, 72.9820},
            new Object[]{"Miraj Cinemas: R Mall", "Mulund", 19.1760, 72.9490},
            new Object[]{"Devgn Cinex", "Mulund", 19.1725, 72.9560}, 
            new Object[]{"PVR: Market City", "Kurla (West)", 19.0874, 72.8888},
            new Object[]{"Bharat Cineplex", "Kurla (West)", 19.0705, 72.8805},
            new Object[]{"INOX: R-City", "Ghatkopar (West)", 19.0998, 72.9164},
            new Object[]{"PVR: Odeon Mall", "Ghatkopar", 19.0865, 72.9070},
            new Object[]{"Movietime Cubic Mall", "Chembur", 19.0490, 72.8980},

            // NAVI MUMBAI
            new Object[]{"Cinepolis: Nexus Seawoods", "Nerul", 19.0195, 73.0185},
            new Object[]{"INOX: Palm Beach", "Vashi", 19.0665, 72.9985},
            new Object[]{"INOX: Raghuleela Mall", "Vashi", 19.0645, 72.9965},
            new Object[]{"Cinepolis: Aurum", "Ghansoli", 19.1245, 73.0130},
            new Object[]{"Fun Square Cinema", "Sanpada", 19.0610, 73.0095},
            new Object[]{"BMX Cinemas", "Littleworld Kharghar", 19.0305, 73.0640},
            new Object[]{"K.K. Cinema", "Kamothe", 19.0200, 73.0850},
            new Object[]{"PVR: Orion Mall", "Panvel", 18.9895, 73.1175},
            new Object[]{"Miraj Cinemas: Cineraj", "Panvel", 18.9910, 73.1205},

            // KALYAN / DOMBIVALI / AMBERNATH / BADLAPUR
            new Object[]{"MovieMax: SM5", "Kalyan", 19.2385, 73.1280},
            new Object[]{"Mukta A2: Triveni Grande", "Kalyan", 19.2410, 73.1320},
            new Object[]{"INOX: Metro Junction", "Kalyan", 19.2245, 73.1180},
            new Object[]{"Miraj Cinemas: Dombivali", "Dombivali (East)", 19.2135, 73.0890},
            new Object[]{"Tilak Cineplex", "Dombivali", 19.2155, 73.0825},
            new Object[]{"Gopi Cinema", "Dombivali", 19.2170, 73.0840},
            new Object[]{"PVR: Lodha Xperia", "Palava", 19.1550, 73.0780},
            new Object[]{"Funcity Big Cinemas", "Ulhasnagar", 19.2220, 73.1550},
            new Object[]{"Miraj Cinemas: Ashok", "Ulhasnagar", 19.2285, 73.1610},
            new Object[]{"Aman Theatre", "Ulhasnagar", 19.2310, 73.1585},
            new Object[]{"Venus Cinema", "Ulhasnagar", 19.2195, 73.1630},
            new Object[]{"BMX Cinemas", "Ambernath", 19.2060, 73.1905},
            new Object[]{"Miraj Cinemas: Star", "Ambernath", 19.1985, 73.1850},
            new Object[]{"Vaishali Cinema", "Badlapur", 19.1620, 73.2260},
            new Object[]{"PVR: Haseen", "Bhiwandi", 19.2945, 73.0650},
            new Object[]{"Nazrana Cinema", "Bhiwandi", 19.2905, 73.0580},

            // VASAI - VIRAR
            new Object[]{"Rassaz Multiplex", "Mira Road", 19.2825, 72.8710},
            new Object[]{"MovieMax: Mira Road", "Mira Road", 19.2850, 72.8735},
            new Object[]{"Maxus Cinemas", "Bhayander", 19.3025, 72.8525},
            new Object[]{"Miraj Cinemas: Dattani", "Vasai", 19.3840, 72.8330},
            new Object[]{"KT Vision Cinema", "Vasai", 19.3855, 72.8305},
            new Object[]{"K Movie Star", "Vasai (West)", 19.3810, 72.8250},
            new Object[]{"PVR: The Capital Mall", "Nalasopara", 19.4140, 72.8155},
            new Object[]{"Miraj Cinemas: Funfiesta", "Nalasopara", 19.4215, 72.8085},
            new Object[]{"Woodland Cinemas", "Virar", 19.4565, 72.7925},
            new Object[]{"Rockstar Nova Cinemaz", "Virar", 19.4605, 72.7985},
            new Object[]{"Movie Max V", "Virar", 19.4650, 72.8010}
        ));                // 3. Populate Delhi-NCR Cinemas (From Screenshots)
         // 3. Populate Delhi-NCR Cinemas (With GPS Coordinates)
     // 3. Populate Delhi-NCR
        seedCinemas("Delhi-NCR", Arrays.asList(
            // DELHI & CENTRAL
            new Object[]{"PVR: Select City Walk", "Saket, District Centre", 28.5284, 77.2194},
            new Object[]{"PVR: Promenade", "Vasant Kunj, Nelson Mandela Road", 28.5428, 77.1558},
            new Object[]{"PVR IMAX with Laser, Priya", "Vasant Vihar, Basant Lok", 28.5579, 77.1637},
            new Object[]{"PVR Director's Cut", "Ambience Mall, Vasant Kunj", 28.5411, 77.1552},
            new Object[]{"Cinepolis: DLF Avenue", "Saket District Centre", 28.5288, 77.2198},
            new Object[]{"INOX: Laserplex, Nehru Place", "Epicuria, TDI South Bridge", 28.5494, 77.2523},
            new Object[]{"INOX: COCA-COLA IMAX Paras", "Nehru Place", 28.5505, 77.2545},
            new Object[]{"PVR: Plaza", "Connaught Place, Outer Circle", 28.6328, 77.2206},
            new Object[]{"INOX: Odeon", "Connaught Place", 28.6334, 77.2182},
            new Object[]{"Delite Cinema", "Asaf Ali Road, Daryaganj", 28.6465, 77.2392},
            new Object[]{"Liberty Cinema", "Karol Bagh, Rohtak Road", 28.6585, 77.1895},
            new Object[]{"PVR: 3CS", "Lajpat Nagar III", 28.5695, 77.2435},
            new Object[]{"Cinepolis: Savitri Complex", "Greater Kailash 2", 28.5375, 77.2435},
            new Object[]{"Batra Reels Cinemas", "New Friends Colony", 28.5630, 77.2690},
            new Object[]{"INOX: Pacific Mall", "Jasola, Mathura Road", 28.5350, 77.2915},
            new Object[]{"PVR: Sangam", "R.K. Puram", 28.5745, 77.1725},
            new Object[]{"PVR: ECX Chanakyapuri", "Yashwant Place", 28.5860, 77.1940},

            // WEST DELHI
            new Object[]{"PVR: Pacific Mall", "Subhash Nagar, Tagore Garden", 28.6433, 77.1044},
            new Object[]{"INOX: Janak Place", "Janakpuri District Centre", 28.6293, 77.0789},
            new Object[]{"Cinepolis: Janak Cinema", "Janakpuri, Pankha Road", 28.6185, 77.0855},
            new Object[]{"PVR: Vikaspuri", "Community Centre, Virjanand Marg", 28.6355, 77.0670},
            new Object[]{"INOX: Patel Nagar", "Shadipur Metro Station", 28.6515, 77.1585},
            new Object[]{"INOX: Vishal Mall", "Rajouri Garden", 28.6495, 77.1215},
            new Object[]{"Wave: Raja Garden", "TDI Paragon Mall, Shivaji Place", 28.6505, 77.1240},
            new Object[]{"INOX: RCube, Monad Mall", "Raja Garden", 28.6510, 77.1265},
            new Object[]{"Miraj Cinemas: Ivory Tower", "Subhash Nagar", 28.6395, 77.1070},

            // NORTH DELHI
            new Object[]{"G3S Cinema", "Rohini Sector 11", 28.7285, 77.1145},
            new Object[]{"Cinepolis: Unity One Mall", "Rohini Sector 10", 28.7235, 77.1120},
            new Object[]{"M2K: Rohini", "Mangalam Place, Sector 3", 28.7015, 77.1140},
            new Object[]{"PVR: Prashant Vihar", "Fun City Mall, Rohini Sec 14", 28.7185, 77.1275},
            new Object[]{"M2K: Pitampura", "Road No. 44, Community Centre", 28.6965, 77.1415},
            new Object[]{"PVR: Cinemagic", "Unity One Elegante, Pitampura", 28.6985, 77.1395},
            new Object[]{"Cinepolis: Pacific NSP2", "Pitampura, NSP Metro", 28.6945, 77.1515},
            new Object[]{"PVR: Shalimar Bagh", "DLF City Centre Mall", 28.7105, 77.1585},
            new Object[]{"Amba Cinema", "Shakti Nagar, Ghanta Ghar", 28.6755, 77.2025},
            new Object[]{"PVR: Anupam", "Saket Community Centre", 28.5255, 77.2115},
            new Object[]{"Miraj Cinemas: Aakash", "Azadpur", 28.7085, 77.1815},

            // EAST DELHI
            new Object[]{"Cinepolis: V3S Mall", "Laxmi Nagar", 28.6375, 77.2845},
            new Object[]{"Cinepolis: Cross River Mall", "Shahdara, Karkardooma", 28.6555, 77.3005},
            new Object[]{"Miraj Cinemas: Vikas Cinemall", "Shahdara", 28.6765, 77.2915},
            new Object[]{"Gagan Theatre", "Nand Nagri", 28.7045, 77.3035},

            // DWARKA
            new Object[]{"PVR: Vegas", "Dwarka Sector 14", 28.5995, 77.0345},
            new Object[]{"PVR: Pacific", "Dwarka Sector 21", 28.5555, 77.0565},

            // NOIDA & GREATER NOIDA
            new Object[]{"PVR: Superplex, Mall Of India", "Noida Sector 18", 28.5676, 77.3211},
            new Object[]{"PVR Director's Cut", "DLF Mall of India, Noida", 28.5674, 77.3215},
            new Object[]{"PVR: Superplex Logix", "Noida City Centre, Sector 32", 28.5746, 77.3560},
            new Object[]{"Wave: Noida", "Sector 18", 28.5694, 77.3218},
            new Object[]{"Miraj Cinemas: TGIP", "The Great India Place, Sector 18", 28.5670, 77.3255},
            new Object[]{"Cinepolis: Modi Mall", "Sector 25A, Noida", 28.5885, 77.3395},
            new Object[]{"MovieMax Laserplex", "Gulshan One 29, Sector 129", 28.5135, 77.3785},
            new Object[]{"MovieMax Edition", "Rcube Monad Mall, Noida", 28.5855, 77.3125},
            new Object[]{"Movietime Cinemas", "Dharam Palace, Sector 18", 28.5715, 77.3245},
            new Object[]{"PVR: Gaur City", "Greater Noida West", 28.6085, 77.4325},
            new Object[]{"US CINEMAS: Galaxy Blue Sapphire", "Greater Noida West", 28.6045, 77.4455},
            new Object[]{"INOX: Omaxe Connaught Place", "Greater Noida, Beta-2", 28.4725, 77.5145},
            new Object[]{"Cinepolis: Grand Venice Mall", "Greater Noida, Pari Chowk", 28.4555, 77.5255},
            new Object[]{"Rajhans Cinemas", "Galaxy Diamond Plaza, Greater Noida", 28.6015, 77.4395},
            new Object[]{"MSX Cinemas", "Greater Noida, Industrial Area", 28.4815, 77.5095},
            new Object[]{"7D Masti: Grand Venice", "Greater Noida", 28.4558, 77.5258},

            // GURUGRAM (GURGAON)
            new Object[]{"PVR: Ambience Mall", "NH-8, Gurugram", 28.5034, 77.0976},
            new Object[]{"PVR Director's Cut", "Ambience Mall, Gurugram", 28.5036, 77.0978},
            new Object[]{"HDFC Millennia PVR: MGF", "MG Road, Gurugram", 28.4807, 77.0803},
            new Object[]{"PVR: City Centre", "DLF Phase 2, MG Road", 28.4795, 77.0825},
            new Object[]{"PVR: Mega Mall", "DLF Phase 1, Golf Course Road", 28.4755, 77.0935},
            new Object[]{"INOX: World Mark", "Sector 65, Maidawas Road", 28.4065, 77.0685},
            new Object[]{"Cinepolis: Airia Mall", "Sector 68, Sohna Road", 28.3965, 77.0495},
            new Object[]{"INOX: AIPL Joy Street", "Sector 66, Badshahpur", 28.4015, 77.0585},
            new Object[]{"PVR: Elan Miracle", "Sector 84, Dwarka Expressway", 28.4115, 76.9665},
            new Object[]{"PVR: Elan Town Centre", "Sector 67, Sohna Road", 28.4045, 77.0535},
            new Object[]{"PVR: Elan Mercado", "Sector 80, NH-8", 28.3715, 76.9585},
            new Object[]{"Devgn CineX", "Elan Epic Mall, Sector 70", 28.3955, 77.0315},
            new Object[]{"Cinepolis: The Esplanade", "Sector 37C", 28.4415, 76.9855},
            new Object[]{"Skylit Cinemas", "Sahara Mall, MG Road", 28.4815, 77.0835},
            new Object[]{"Wave: Urbana Premium", "Sector 67", 28.3985, 77.0625},
            new Object[]{"MovieMax: Ansal Plaza", "Palam Vihar", 28.5145, 77.0415},
            new Object[]{"INOX: Sapphire 90", "Sector 90", 28.4015, 76.9365},
            new Object[]{"INOX: Ardee Mall", "Sector 52", 28.4385, 77.0825},
            new Object[]{"INOX: Gurgaon Sapphire 83", "Sector 83", 28.3985, 76.9635},
            new Object[]{"INOX: IRIS Broadway", "Sector 85", 28.4045, 76.9515},
            new Object[]{"Miraj Maximum", "Metropolis Mall, MG Road", 28.4775, 77.0755},
            new Object[]{"Miraj Cinemas: KLJ Square", "Sector 83", 28.3995, 76.9655},
            new Object[]{"Connplex Cinemas", "Floreal Towers, Sector 83", 28.3975, 76.9685},
            new Object[]{"Cineport Cinemas", "SVH Metro Street, Sector 83", 28.3965, 76.9695},
            new Object[]{"Mad Cinemas", "Newtown Square, Sector 95A", 28.4155, 76.9185},
            new Object[]{"RR Cinema: Omaxe", "Sector 49, Sohna Road", 28.4175, 77.0425},
            new Object[]{"Cinepolis: Grand View", "Sector 58", 28.4125, 77.1085},
            new Object[]{"QLA Cinemas", "Dremz Mall, Old Railway Road", 28.4685, 77.0265},
            new Object[]{"Movietime Cinemas", "Celebration Mall, Sohna Road", 28.4235, 77.0395},
            new Object[]{"Legend Cinema Lounges", "Mall Fifty One, Sector 51", 28.4415, 77.0725},

            // GHAZIABAD & INDIRAPURAM
            new Object[]{"PVR: Mahagun", "Vaishali Sector 3", 28.6415, 77.3395},
            new Object[]{"PVR: EDM", "Kaushambi, East Delhi Mall", 28.6435, 77.3155},
            new Object[]{"Wave: Kaushambi", "The Wave Mall", 28.6445, 77.3175},
            new Object[]{"PVR: VVIP", "Raj Nagar Extension", 28.7085, 77.4325},
            new Object[]{"PVR: Opulent", "Grand Trunk Road", 28.6655, 77.4395},
            new Object[]{"INOX: Shipra Mall", "Indirapuram", 28.6345, 77.3695},
            new Object[]{"US CINEMAS: Aditya Mall", "Indirapuram", 28.6395, 77.3725},
            new Object[]{"US CINEMAS: Eros Mall", "Indirapuram", 28.6325, 77.3655},
            new Object[]{"RR Cinema: Jaipuria Mall", "Indirapuram", 28.6415, 77.3755},
            new Object[]{"MovieMax: Pacific Mall", "Sahibabad", 28.6425, 77.3155},
            new Object[]{"Wave Cinemas: Gaur Central", "RDC Raj Nagar", 28.6915, 77.4475},
            new Object[]{"B18 Cinemas", "City Center, Mohan Nagar", 28.6785, 77.3825},
            new Object[]{"Devgn CineX", "Ansa City Centre, Rajnagar Ext", 28.7125, 77.4365},
            new Object[]{"Galaxie Multiplex", "Sahibabad", 28.6685, 77.3625},
            new Object[]{"7D Masti: EDM Mall", "Kaushambi", 28.6432, 77.3152},
            new Object[]{"7D Masti: Shipra Mall", "Indirapuram", 28.6342, 77.3692},
            new Object[]{"ROONGTA CINEMAS", "Shopprix Mall, Vaishali", 28.6355, 77.3455},
            new Object[]{"Silvercity Multiplex", "G.T. Road", 28.6635, 77.4315},
            new Object[]{"US Cinemas: Movie World", "Sihani Chungi", 28.6855, 77.4125},
            new Object[]{"Movie Magic Cinema", "Loni", 28.7485, 77.2885},
            new Object[]{"Kavita Multiplex", "Loni", 28.7515, 77.2915},
            new Object[]{"Meenakshi Multiplex", "Loni", 28.7465, 77.2855},
            new Object[]{"Miraj Cinemas: M4U", "Sahibabad", 28.6825, 77.3515},
            new Object[]{"Madhuban Cinema", "Dasna", 28.6715, 77.5265},

            // FARIDABAD
            new Object[]{"PVR: Pebble Downtown", "Sector 12", 28.3915, 77.3245},
            new Object[]{"PVR: Pacific Mall", "NIT Bus Stand", 28.3885, 77.2955},
            new Object[]{"INOX: Crown Interiorz", "Mathura Road", 28.4685, 77.3095},
            new Object[]{"INOX: EF3 Mall", "Sector 20A", 28.4115, 77.3125},
            new Object[]{"PVR: Piyush Mahendra", "Metro Road", 28.3585, 77.3155},
            new Object[]{"Miraj Cinemas: Eldeco", "Sector 12", 28.3935, 77.3215},
            new Object[]{"MSX Silvercity", "Haldiram Citymall, Sector 12", 28.3925, 77.3235},
            new Object[]{"Fun Cinemas: Parsvnath", "Manhattan Mall, Sector 20A", 28.4135, 77.3115},
            new Object[]{"AKR Cinemas", "SLF Mall, Sector 32", 28.4725, 77.3225},
            new Object[]{"Pristine Mall", "Sector 31", 28.4655, 77.3185},

            // PILKHUWA & KUNDLI
            new Object[]{"Satyam VS Cinema", "Pilkhuwa", 28.7035, 77.6535},
            new Object[]{"Vibhor Chitralok", "Pilkhuwa", 28.7015, 77.6515},
            new Object[]{"AKR Cinemas", "TDI Mall, Kundli", 28.8685, 77.1085},
            new Object[]{"eBox Cinema", "Parker Mall, Kundli", 28.8725, 77.1125},
            new Object[]{"eBox Cinema", "Sonic World Mall, Surajpur", 28.5145, 77.5185}
        		));          // 5. Populate Bengaluru Cinemas (With GPS Coordinates)
     // 4. Populate Bengaluru
        seedCinemas("Bengaluru", Arrays.asList(
            // MALLS & MULTIPLEXES - CENTRAL
            new Object[]{"PVR: Orion Mall", "Dr Rajkumar Road, Malleshwaram", 13.0112, 77.5549},
            new Object[]{"INOX: Mantri Square", "Malleshwaram", 12.9915, 77.5702},
            new Object[]{"PVR: Renaissance", "Malleshwaram", 13.0035, 77.5640},
            new Object[]{"INOX: Lido", "Off MG Road, Ulsoor", 12.9733, 77.6203},
            new Object[]{"PVR: Director's Cut, Forum Rex Walk", "Brigade Road", 12.9738, 77.6075},
            new Object[]{"Swagath ShankarNag (ONYX) LED", "MG Road", 12.9745, 77.6092},
            new Object[]{"INOX: Garuda Mall", "Magrath Road", 12.9701, 77.6097},
            new Object[]{"Gopalan Cinemas: Arcade Mall", "Mysore Road", 12.9332, 77.5165},
            new Object[]{"Gopalan Mall: Sirsi Circle", "Mysore Road", 12.9615, 77.5665},

            // KORAMANGALA & HSR & BTM
            new Object[]{"PVR: Nexus (Formerly Forum)", "Koramangala", 12.9344, 77.6112},
            new Object[]{"Cinephile PNR Felicity Mall", "HSR Layout", 12.9135, 77.6372},
            new Object[]{"Sandhya RGB Laser Atmos", "BTM Layout", 12.9165, 77.6105},
            new Object[]{"Balaji Theatre", "Tavarekere (Near BTM)", 12.9235, 77.6152},
            new Object[]{"Lakshmi Cinema 4K Dolby Atmos", "Tavarekere", 12.9215, 77.6125},
            new Object[]{"Srinivasa Cinema 4K Dolby Atmos", "SG Palya", 12.9305, 77.6042},

            // SOUTH BENGALURU (JP NAGAR, BANNERGHATTA, ETC)
            new Object[]{"PVR: Vega City", "Bannerghatta Road", 12.9075, 77.6005},
            new Object[]{"Cinepolis: Royal Meenakshi Mall", "Bannerghatta Road", 12.8772, 77.5955},
            new Object[]{"Cinepolis: SJR (Central Mall)", "Arekere, Bannerghatta", 12.8855, 77.5965},
            new Object[]{"Gopalan Cinemas", "Bannerghatta Road", 12.9035, 77.6002},
            new Object[]{"INOX: Central", "JP Nagar, Mantri Junction", 12.9125, 77.5905},
            new Object[]{"Siddeshwara 4K Dolby Atmos 3D", "JP Nagar", 12.9085, 77.5852},
            new Object[]{"Sri Renuka Prasanna Theatre", "JP Nagar", 12.9105, 77.5805},
            new Object[]{"PVR: Superplex Forum Mall", "Kanakapura Road", 12.8735, 77.5562},
            new Object[]{"Manasa RGB Laser ATMOS", "Konanakunte", 12.8795, 77.5605},
            new Object[]{"Sri Lakshmi A/C 4k Dolby 7.1", "Gottigere", 12.8575, 77.5875},
            new Object[]{"Kamakya 4K Dolby Atmos", "Banashankari", 12.9245, 77.5545},
            new Object[]{"Mahadeshwara Cinema 4K", "Banashankari", 12.9265, 77.5505},
            new Object[]{"Sri Venkateshwara Digital 4K", "Girinagar", 12.9425, 77.5425},

            // EAST BENGALURU (WHITEFIELD, MARATHAHALLI)
            new Object[]{"PVR: Phoenix Marketcity", "Whitefield Road", 12.9965, 77.6965},
            new Object[]{"PVR: VR Bengaluru", "Whitefield Road", 12.9962, 77.6952},
            new Object[]{"Cinepolis: Nexus Shantiniketan", "Whitefield", 12.9895, 77.7285},
            new Object[]{"INOX: Nexus", "Whitefield", 12.9605, 77.7465},
            new Object[]{"PVR: Aura Park Square", "Whitefield", 12.9635, 77.7385},
            new Object[]{"INOX: Brookefield Mall", "Kundalahalli", 12.9665, 77.7185},
            new Object[]{"INOX: SBR Horizon", "Seegehalli, Whitefield", 13.0115, 77.7625},
            new Object[]{"PVR: Soul Spirit Central Mall", "Bellandur", 12.9265, 77.6755},
            new Object[]{"Sri Vinayaka Cinemas 4K Dolby", "Varthur", 12.9395, 77.7445},
            new Object[]{"Kino Cinemas", "Seegehalli, Kadugodi", 13.0035, 77.7605},

            // NORTH BENGALURU (YELAHANKA, HEBBAL)
            new Object[]{"INOX: Megaplex Mall of Asia", "Byatarayanapura, Yelahanka", 13.0675, 77.5935},
            new Object[]{"INOX: Galleria Mall", "Yelahanka", 13.1005, 77.5965},
            new Object[]{"PVR: Bhartiya Mall of Bengaluru", "Thanisandra Main Road", 13.0695, 77.6415},
            new Object[]{"PVR: MSR Elements Mall", "Tanisandra Main Road", 13.0455, 77.6265},
            new Object[]{"PVR: Vaishnavi Sapphire Mall", "Yeshwanthpur", 13.0275, 77.5495},
            new Object[]{"Ganesh Digital 2K Cinema", "Yelahanka New Town", 13.1002, 77.5805},
            new Object[]{"Prakash Theatre", "Yelahanka", 13.1025, 77.5905},
            new Object[]{"Rockline Cinemas", "Jalahalli Cross", 13.0445, 77.5145},
            new Object[]{"HMT Cinema", "Jalahalli", 13.0375, 77.5525},
            new Object[]{"Goverdhan Theatre", "Yeshwantpur", 13.0235, 77.5485},
            new Object[]{"Murali Cinemas (Gokula)", "Mathikere", 13.0335, 77.5585},
            new Object[]{"Sri Radhakrishna Theatre", "RT Nagar", 13.0235, 77.5965},
            new Object[]{"Venkateshwara Cinemas 4K", "Gollarahati", 12.9885, 77.4945},

            // OLD MADRAS ROAD & KR PURAM
            new Object[]{"Gopalan Grand Mall", "Old Madras Road", 12.9935, 77.6615},
            new Object[]{"PVR: Orion Uptown", "Old Madras Road", 13.0335, 77.7635},
            new Object[]{"Gopalan Miniplex: Signature Mall", "Old Madras Road", 12.9925, 77.6605},
            new Object[]{"Venkateshwara A/c 4K Dolby Atmos", "K.R.Puram", 13.0085, 77.6935},
            new Object[]{"Movietime Cinemas: YGR Signature", "RR Nagar", 12.9325, 77.5175}, 
            new Object[]{"Pushpanjali B N Pura", "Dooravani Nagar", 13.0075, 77.6695},

            // ELECTRONIC CITY & HOSUR ROAD
            new Object[]{"INOX: M5 Ecity", "Veerasandra Industrial Area", 12.8365, 77.6685},
            new Object[]{"Venkateshwara Theatre", "Konappana Agrahara (E.City)", 12.8555, 77.6645},
            new Object[]{"Sri Krishna Lazer Projection 4K", "Bomanahalli", 12.9045, 77.6235},
            new Object[]{"Brundha RGB Laser 4K", "Hongasandra", 12.8985, 77.6325},
            new Object[]{"Galaxy Paradise", "Begur Road", 12.8795, 77.6275},

            // FAMOUS SINGLE SCREENS (GANDHINAGAR & MAJESTIC)
            new Object[]{"Santosh 4K Dolby Theatre", "Gandhinagar", 12.9775, 77.5785},
            new Object[]{"Narthaki 4K Dolby 7.1", "Gandhinagar", 12.9772, 77.5792},
            new Object[]{"Triveni Theatre A/C 3D 4K", "Gandhinagar", 12.9765, 77.5775},
            new Object[]{"Anupama Theatre A/C 4K", "Gandhinagar", 12.9778, 77.5772},
            new Object[]{"Bhumika Digital 2K Cinema", "Gandhinagar", 12.9768, 77.5778},
            new Object[]{"Abhinay Theatre 4K A/C", "Gandhinagar", 12.9785, 77.5795},
            new Object[]{"Sapna 2K Dolby 5.1", "Gandhinagar", 12.9762, 77.5788},
            new Object[]{"Movieland Theatre", "Gandhinagar", 12.9785, 77.5765},
            new Object[]{"Savitha Theatre", "Malleshwaram", 12.9975, 77.5695},
            new Object[]{"Navrang Theatre", "Rajaji Nagar", 12.9925, 77.5555},
            new Object[]{"Veeresh Cinemas", "Magadi Road", 12.9755, 77.5455},
            new Object[]{"Prasanna Digital 4K Cinema", "Magadi Road", 12.9745, 77.5485},
            new Object[]{"Anjan Digital 4K A/C Cinema", "Magadi Road", 12.9705, 77.5305},
            new Object[]{"PVR: GT World Mall", "Magadi Road", 12.9735, 77.5452},

            // OUTSKIRTS (RR NAGAR, KENGERI, ETC)
            new Object[]{"Cinepolis: Binnypet Mall", "Binneypet", 12.9635, 77.5575},
            new Object[]{"PVR: Global Mall", "Mysore Road", 12.9395, 77.5255},
            new Object[]{"Robin Theater 4K Dolby Atmos", "Kengeri", 12.9155, 77.4835},
            new Object[]{"Venkateshwara Digital 4K", "Kengeri", 12.9105, 77.4805},
            new Object[]{"Miraj Cinemas: TGS Lotus Elite", "Sunkadakatte", 12.9905, 77.5055},
            new Object[]{"Mohan Cinema Barco 4K", "Sunkadakatte", 12.9885, 77.5085},
            new Object[]{"Victory Cinema Barco-4K", "Kamakshipalya", 12.9805, 77.5185},
            new Object[]{"Akash Cinemas", "Laggere", 13.0185, 77.5085},
            new Object[]{"VR Cinemas 4K A/C", "Mallathalli", 12.9605, 77.4985},
            new Object[]{"Deepak Talkies", "Bidadi", 12.7985, 77.3855}
        		));          // 6. Populate Hyderabad Cinemas (With GPS Coordinates)
     // 5. Populate Hyderabad
        seedCinemas("Hyderabad", Arrays.asList(
            // HITEC CITY, GACHIBOWLI & MADHAPUR
            new Object[]{"AMB Cinemas", "Gachibowli", 17.4567, 78.3644}, // Sarath City Capital Mall
            new Object[]{"PVR ICON: Hitech", "Madhapur", 17.4504, 78.3809},
            new Object[]{"PVR: Inorbit Cyberabad", "Hitech City, Madhapur", 17.4338, 78.3871},
            new Object[]{"Prasads Multiplex", "Khairtabad, NTR Gardens", 17.4137, 78.4735}, // IMAX
            new Object[]{"PVR: Atrium", "Gachibowli", 17.4425, 78.3582},
            new Object[]{"PVR: Preston Prime", "Gachibowli", 17.4526, 78.3614},
            new Object[]{"Platinum Movietime: SLN Terminus", "Gachibowli", 17.4534, 78.3639},
            new Object[]{"BR Hitech 70mm", "Madhapur", 17.4502, 78.3840},
            new Object[]{"Aparna Cinemas", "Nallagandla", 17.4687, 78.3182},

            // KUKATPALLY & MIYAPUR
            new Object[]{"PVR: Nexus Mall", "Kukatpally", 17.4842, 78.3891}, // Formerly Forum Sujana
            new Object[]{"Asian Lakshmikala Cinepride", "Moosapet", 17.4660, 78.4190},
            new Object[]{"Sree Ramulu 70mm 4k Laser", "Moosapet", 17.4665, 78.4185},
            new Object[]{"Mallikarjuna 70MM A/C DTS", "Kukatpally", 17.4935, 78.4060},
            new Object[]{"Bhramaramba 70MM A/C 4K Dolby", "Kukatpally", 17.4936, 78.4062},
            new Object[]{"INOX: Ashoka One Mall", "Kukatpally Y Junction", 17.4622, 78.4140},
            new Object[]{"Arjun 70MM", "Kukatpally", 17.4938, 78.4022},
            new Object[]{"Miraj Cinemas: CineTown", "Miyapur", 17.4962, 78.3491},
            new Object[]{"Sai Ranga 70MM 4k Dolby 7.1", "Miyapur", 17.5020, 78.3565},
            new Object[]{"Asian Cineplanet Multiplex", "Kompally", 17.5458, 78.4754},
            new Object[]{"GPR Multiplex", "Nizampet", 17.5118, 78.3812},

            // SECUNDERABAD & NORTH
            new Object[]{"MovieMax: AMR Planet", "ECIL, Secunderabad", 17.4815, 78.5684},
            new Object[]{"Tivoli Cinemas", "Secunderabad", 17.4475, 78.4912},
            new Object[]{"Prashant Cinema", "Secunderabad", 17.4455, 78.5020},
            new Object[]{"Asian Radhika Multiplex", "ECIL", 17.4802, 78.5631},
            new Object[]{"Cinepolis: TNR North City", "Suchitra", 17.5125, 78.4745},
            new Object[]{"Sri Sai Ram 70mm A/C", "Malkajgiri", 17.4468, 78.5325},
            new Object[]{"Cinepolis: CCPL Mall", "Malkajgiri", 17.4505, 78.5345},
            new Object[]{"Miraj Cinemas: Raghavendra", "Malkajgiri", 17.4475, 78.5350},
            new Object[]{"Asian Sha & Shahensha", "Chintal", 17.5185, 78.4415},
            new Object[]{"Bhujanga 70MM", "Jeedimetla", 17.5195, 78.4425},
            new Object[]{"Asian Mukund Cinema", "Medchal", 17.6295, 78.4835},
            new Object[]{"Lakshmi Kala Mandir", "Alwal", 17.4985, 78.5140},
            new Object[]{"Talluri Theatres", "Kushaiguda", 17.4835, 78.5755},
            new Object[]{"VLS Sridevi 2K A/C Dts", "Chilakalguda", 17.4285, 78.5085},

            // CENTRAL & BANJARA HILLS
            new Object[]{"PVR: Next Galleria Mall", "Panjagutta", 17.4265, 78.4525},
            new Object[]{"PVR: Central Mall", "Panjagutta", 17.4248, 78.4518},
            new Object[]{"INOX: GVK One", "Banjara Hills", 17.4192, 78.4484},
            new Object[]{"PVR: RK Cineplex", "Banjara Hills", 17.4145, 78.4355},
            new Object[]{"Connplex Cinemas: MPM Mall", "Banjara Hills", 17.4085, 78.4535}, // MPM Timesquare
            new Object[]{"PVR: Irrum Manzil", "Khairatabad", 17.4168, 78.4578},
            new Object[]{"Asian Mukta A2 Sensation", "Khairatabad", 17.4145, 78.4615},
            new Object[]{"AAA Cinemas", "Ameerpet", 17.4375, 78.4465},
            new Object[]{"INOX: Sattva Necklace Mall", "Kavadiguda", 17.4165, 78.4845},
            new Object[]{"Sree Sai Raja Theatre", "Musheerabad", 17.4125, 78.4985},

            // RTC X ROADS (THE HUB)
            new Object[]{"Sandhya 70MM 4K Dolby Atmos", "RTC X Roads", 17.4065, 78.4948},
            new Object[]{"Sandhya 35mm 2k Dolby Atmos", "RTC X Roads", 17.4062, 78.4945},
            new Object[]{"Sudarshan 35MM 4k Laser", "RTC X Roads", 17.4072, 78.4952},
            new Object[]{"Devi 70MM 4K Laser", "RTC X Roads", 17.4075, 78.4955},
            new Object[]{"Saptagiri 70MM 4K", "RTC X Roads", 17.4082, 78.4965},

            // EAST (UPPAL, DILSUKHNAGAR, LB NAGAR)
            new Object[]{"Cinepolis: DSL Virtue Mall", "Uppal", 17.4025, 78.5605},
            new Object[]{"Asian Rajya Lakshmi", "Uppal", 17.3985, 78.5585},
            new Object[]{"Sri Krishna 70MM", "Uppal", 17.3965, 78.5595},
            new Object[]{"PVR: Musarambagh", "Malakpet", 17.3735, 78.5145},
            new Object[]{"Miraj Cinemas: Shalini Shivani", "Kothapet", 17.3685, 78.5445},
            new Object[]{"BVK Multiplex Vijayalakshmi", "LB Nagar", 17.3495, 78.5495},
            new Object[]{"Cine Town Indra Nagendra", "Karmanghat", 17.3415, 78.5305},
            new Object[]{"Sree Ramana 70MM 4K", "Amberpet", 17.3915, 78.5185},
            new Object[]{"ART CINEMAS", "Vanasthalipuram", 17.3375, 78.5705},
            new Object[]{"Sushma 2K Dolby Digital", "Vanasthalipuram", 17.3385, 78.5725},
            new Object[]{"Santosh Theatre", "Ibrahimpatnam", 17.1945, 78.6475},

            // SOUTH & OLD CITY
            new Object[]{"Cinepolis: Lulu Mall", "Hyderabad", 17.4895, 78.3915}, // Manjeera Mall, Kukatpally (Lulu Hyd location)
            new Object[]{"Cinepolis: Mantra Mall", "Attapur", 17.3688, 78.4335},
            new Object[]{"Asian M Cube Mall", "Attapur", 17.3715, 78.4315},
            new Object[]{"Cinepolis: Sudha Cinemas", "Bahadurpura", 17.3515, 78.4565},
            new Object[]{"Metro Cinema", "Bahadurpura", 17.3525, 78.4575},
            new Object[]{"Laxmi 70MM A/C LASER", "Shamshabad", 17.2565, 78.4315},
            new Object[]{"Asian Super Cinema", "Balapur", 17.3185, 78.4985},
            new Object[]{"Yakut Mahal Theater", "Yakutpura", 17.3545, 78.4875},
            new Object[]{"Ramakrishna Glitterati", "Abids", 17.3885, 78.4745},
            new Object[]{"Rama Krishna 70mm", "Abids", 17.3882, 78.4742},
            new Object[]{"Aradhana Theatre", "Hyderabad", 17.3585, 78.4985}, // Near Charminar area
            new Object[]{"Alankar (Pratap Theatre)", "Langer House", 17.3785, 78.4165},

            // OTHER AREAS
            new Object[]{"INOX: GSM Mall", "Miyapur", 17.4985, 78.3405},
            new Object[]{"JP Cinemas", "Chandanagar", 17.4875, 78.3285},
            new Object[]{"Miraj Cinemas: Geeta", "Chandanagar", 17.4865, 78.3275},
            new Object[]{"Miraj Cinemas: Anand Mall", "Narsingi", 17.3825, 78.3685},
            new Object[]{"INOX: Prism Mall", "Gachibowli", 17.4385, 78.3455},
            new Object[]{"Asian Cinemart", "RC Puram", 17.5125, 78.3035},
            new Object[]{"Asian Jyothi", "RC Puram", 17.5135, 78.3045},
            new Object[]{"Gokul 70MM 4K DTS", "Erragadda", 17.4485, 78.4385},
            new Object[]{"Movietime Cinemas: SKY Mall", "Erragadda", 17.4495, 78.4395},
            new Object[]{"Asian Tarakarama Cineplex", "Kachiguda", 17.3875, 78.4955},
            new Object[]{"INOX: Maheshwari Parmeshwari", "Kachiguda", 17.3882, 78.4962},
            new Object[]{"Indra Venkataramana Padmavati", "Kachiguda", 17.3895, 78.4975},
            new Object[]{"PVR: Lakeshore Mall", "Y Junction", 17.4615, 78.4135},
            new Object[]{"ROONGTA CINEMAS: NOVUM", "Nampally", 17.3925, 78.4685},
            new Object[]{"UK Cineplex", "Nacharam", 17.4245, 78.5485},
            new Object[]{"Vyjayanthi Cinema A/C 2K", "Nacharam", 17.4265, 78.5505},
            new Object[]{"INOX: SMR Vinay Metro Mall", "Miyapur", 17.4955, 78.3585},
            new Object[]{"Venkata Sai Theatre AC", "Keesara", 17.5215, 78.6785},
            new Object[]{"Sri Krishna Theatre", "Aliabad", 17.3485, 78.4885}
        		));          // 2. Populate Chandigarh Cinemas (With GPS Coordinates)
     // 10. Populate Chandigarh
        seedCinemas("Chandigarh", Arrays.asList(
            // CHANDIGARH CITY
            new Object[]{"PVR: Elante, Chandigarh", "Plot No 178-178 A, 3rd Floor, Elante Mall, Industrial Area Phase I", 30.7055, 76.8015},
            new Object[]{"Piccadily Square: Chandigarh", "Sub. City Center, Sector 34-A, Chandigarh", 30.7275, 76.7755},
            new Object[]{"PVR: Centra, Chandigarh", "177/D, Centra Mall, Industrial Phase-1, Near Tribune Chowk", 30.7065, 76.7965},
            new Object[]{"Cinepolis: Jagat Mall, Chandigarh", "Plot No 32, TDI Mall, Sector 17 A, Chandigarh", 30.7405, 76.7865},
            new Object[]{"PVR: City Centre IT Park, Chandigarh", "Plot No-22/23, 2nd Floor, DLF City Center Mall, I.T Park, Manimajra", 30.7255, 76.8375},
            new Object[]{"Fun Cinemas: Republic Mall, Chandigarh", "Dhillon Complex, Mani Majra, Chandigarh", 30.7185, 76.8435},

            // MOHALI
            new Object[]{"PVR: CP67 Mall, Mohali", "2nd Floor CP67 Mall, Sector 67, Sahibzada Ajit Singh Nagar", 30.6815, 76.7345},
            new Object[]{"PVR: VR Punjab Mall, Mohali", "NH21, North Country Mall, 2nd Floor, Sector 118, Sahibzada Ajit Singh Nagar", 30.7375, 76.6785},
            new Object[]{"PVR: MOHALI WALK", "rd Floor, Plot No. 1-2, Sector- 62, SAS NAGAR", 30.6955, 76.7225},
            new Object[]{"Cinepolis: Bestech Square, Mohali", "Bestech Square Mall, Sector 66, Sahibzada Ajit Singh Nagar", 30.6725, 76.7415},

            // ZIRAKPUR
            new Object[]{"PVR Cosmo: Zirakpur", "Cosmo Mall, Ambala Chandigarh Express Highway, Zirakpur", 30.6485, 76.8185},
            new Object[]{"INOX: Dhillon Plaza (Zirakpur)", "2nd Floor, Dhillon Square Mall, Chhatbir Road", 30.6355, 76.8245},
            new Object[]{"Legend Cinemas: Paras Downtown Square, Zirakpur", "Paras Down Town Square Mall, Ambala Chandigarh Express Way", 30.6445, 76.8205},

            // PANCHKULA
            new Object[]{"Rajhans Cinemas: Panchkula", "Hi5 Mall, Sector 5, Opposite KC Theater, Panchkula", 30.7045, 76.8525},
            new Object[]{"INOX: NH22 Mall, Amravati Enclave, Panchkula", "Amravati, Enclave, Chandigarh, Chandigarh", 30.7685, 76.8985}
        		));          // 7. Populate Ahmedabad Cinemas (With GPS Coordinates)
     // 9. Populate Ahmedabad
        seedCinemas("Ahmedabad", Arrays.asList(
            // WEST AHMEDABAD (Thaltej, Bodakdev, Satellite, Vastrapur)
            new Object[]{"PVR: Palladium Mall, Ahmedabad", "4th Floor, Palladium Mall, SG Highway, Thaltej", 23.0565, 72.5225},
            new Object[]{"PVR: Acropolis, Ahmedabad", "Acropolis Mall, Thaltej Cross Road", 23.0515, 72.5235},
            new Object[]{"Cinepolis: Nexus Ahmedabad One", "Alpha One Mall, Vastrapur Lake", 23.0395, 72.5315},
            new Object[]{"Mukta A2 Cinemas: The Retail Park (TRP) Bopal", "The Retail Park, Bopal", 23.0295, 72.4695},
            new Object[]{"AB Miniplex: Shivranjini Cross Road", "Sheetal Varsha, Satellite Road", 23.0265, 72.5335},
            new Object[]{"Connplex Cinemas: Prahladnagar", "Campus Corner 2, Prahlad Nagar", 23.0115, 72.5125},
            new Object[]{"Connplex Cinemas: Shilaj", "Shilp The Address, Shilaj Road", 23.0535, 72.4835},
            new Object[]{"Connplex Cinemas (Luxuriance): SBR", "Shilp Epitome, Bodakdev", 23.0415, 72.5085},
            new Object[]{"Mukta A2 Cinemas: Ratnanjali Square Satellite", "Ratnanjali Infra LLP, Satellite", 23.0315, 72.5235},
            new Object[]{"Sanelite Cinemas (Banana Smartplex): SBR", "Times Square Grand, Thaltej", 23.0555, 72.5055},
            new Object[]{"City Pulse Miniplex: Iscon Circle", "Baleshwar Square, SG Highway", 23.0265, 72.5085},
            new Object[]{"1,Newfangled Miniplex (Twin Seat): Mondeal Park", "Mondeal Retail Park, SG Road", 23.0268, 72.5082},
            new Object[]{"Magic Cinema: YMCA Club", "YMCA International Center, SG Highway", 23.0035, 72.5025},

            // NORTH AHMEDABAD (Motera, Chandkheda, Sabarmati)
            new Object[]{"Rajhans Cinemas: The CBD Mall", "Zundal Circle, SP Ring Road", 23.1295, 72.5635},
            new Object[]{"PVR: Arved Transcube", "Ranip, Ahmedabad", 23.0855, 72.5845},
            new Object[]{"Devgn CineX: Chandkheda", "Aamrakunj Arne, Nigam Nagar", 23.1115, 72.5875},
            new Object[]{"PVR: Motera, Ahmedabad", "4D Square Mall, Visat-Gandhinagar Highway", 23.1065, 72.5965},
            new Object[]{"Sanelite Cinemas: Agora Mall", "Agora Mall, Motera", 23.1125, 72.5935},
            new Object[]{"Mukta A2 Cinemas: Chandkheda", "KB Royal Pheonix, Chandkheda", 23.1145, 72.5915},
            new Object[]{"LHD Cinemas: Chandkheda", "Sai Sapphire Square, Chandkheda", 23.1165, 72.5895},
            new Object[]{"City Gold: Motera", "Indira Nagar, Motera Stadium Road", 23.1025, 72.6025},
            new Object[]{"2,Newfangled Miniplex (Twin Seat): Motera", "North Plaza, Motera", 23.1085, 72.5985},

            // EAST AHMEDABAD (Maninagar, Vastral, Nikol, Naroda)
            new Object[]{"Rajhans Cinemas: Vastral", "Ved Arcade Mall, SP Ring Road", 23.0065, 72.6685},
            new Object[]{"Mango Plus Cinemas: Nikol", "Fortune Sky, Fortune Circle", 23.0515, 72.6735},
            new Object[]{"Cinepolis: Kankubag, Vastral", "Nirant Cross Road, Metro Station", 22.9985, 72.6585},
            new Object[]{"Miraj Cinemas: City Pulse", "Kankaria Road, Raipur", 23.0165, 72.6035},
            new Object[]{"Rajhans Cinemas: Nikol", "Pavillion Mall, New Nikol", 23.0545, 72.6815},
            new Object[]{"PVR: Satyamev Emporio Odhav", "Satyamev Emporio Mall, Odhav", 23.0235, 72.6785},
            new Object[]{"Apple Multiplex: Maninagar", "Gita Mandir Road, Maninagar", 23.0035, 72.5965},
            new Object[]{"Miraj Cinemas: Cinepride", "Krishna Nagar, Naroda-Narol", 23.0615, 72.6535},
            new Object[]{"Devi Multiplex: Naroda", "Naroda, Near Axis Bank ATM", 23.0725, 72.6615},
            new Object[]{"Apple Cinema: Bapunagar", "White House, India Colony", 23.0445, 72.6365},
            new Object[]{"Havelock Cineflix Cinemas: Maninagar", "Eka Club by TransStadia, Kankaria Lake", 23.0115, 72.6015},
            new Object[]{"Cineprime Cinema: Nikol", "Hiltown Square, MG Road", 23.0485, 72.6835},
            new Object[]{"Miraj Cinemas: Vitthal Plaza", "New Naroda Dahegam Road", 23.0865, 72.6845},
            new Object[]{"Orange Cinemas: Bapunagar", "Pushkar Business Park, Bapunagar", 23.0415, 72.6325},
            new Object[]{"Mira Cinema: Ahmedabad", "Bhairavnath Road, Maninagar", 22.9925, 72.6015},
            new Object[]{"Revolution Multiplex, CTM", "CTM Cross Road", 23.0015, 72.6345},

            // GANDHINAGAR & OUTSKIRTS
            new Object[]{"Devgn CineX: Swagat Mall", "Sargasan, Gandhinagar", 23.1935, 72.6215},
            new Object[]{"Connplex Cinemas (Signature): Gandhinagar", "Shreeji Signature, Sargasan", 23.1985, 72.6265},
            new Object[]{"Classic Cinema, Kudasan", "Pramukh Anand Mall, Gandhinagar", 23.1865, 72.6385},
            new Object[]{"White Screen Cinema (Five 11)", "Indroda Park, Gandhinagar", 23.2085, 72.6635},
            new Object[]{"Connplex Cinemas (Luxuriance): Vaishnodevi", "Master Avenue, Gandhinagar", 23.1435, 72.5565},
            new Object[]{"The Cinestar Miniplex: Bhat Circle", "Xperia, SP Ring Road, Gandhinagar", 23.1235, 72.6065},

            // CENTRAL & OTHER AREAS
            new Object[]{"INOX: Himalaya Mall", "Drive In Road, Memnagar", 23.0515, 72.5315},
            new Object[]{"City Gold: Ashram Road", "Muslim Society, Navrangpura", 23.0365, 72.5715},
            new Object[]{"Connplex Cinemas: Parimal", "Parimal Garden, Ambavadi", 23.0235, 72.5565},
            new Object[]{"Apple Multiplex: Gota", "Vandematram, Gota Road", 23.0935, 72.5415},
            new Object[]{"Connplex Cinemas (Signature): Gota", "The Link, Gota", 23.1015, 72.5435},
            new Object[]{"City Gold Satellite: Ahmedabad", "Shyamal Cross Road", 23.0215, 72.5285},
            new Object[]{"Sanelite Cinemas: Science City", "The Obelisk, Science City Road", 23.0835, 72.5015},
            new Object[]{"Mukta A2 Shiv Cinema", "Ashram Road", 23.0335, 72.5735},
            new Object[]{"Mukta A2 Cinemas: Rajyash", "South Vasna", 23.0035, 72.5565},
            new Object[]{"Connplex Cinemas: South Bopal", "Gala Gymkhana Road, South Bopal", 23.0335, 72.4635},
            new Object[]{"City Gold: Bopal", "Amrapali Shopping Complex, Bopal", 23.0365, 72.4615},
            new Object[]{"Miraj Cinemas: Vardhman Square", "Sanand, Ahmedabad", 22.9915, 72.3815},
            new Object[]{"SK Cinemas: Hathijan", "Lalgebi Circle, Hathijan", 22.9515, 72.6535},
            new Object[]{"Cineprime Cinema: Luxuriance", "Vishala Empire, Hanspura", 23.0815, 72.6865},
            new Object[]{"City Gold: Jivraj Park", "Avadh Hotel, Jivraj Park", 23.0115, 72.5315},
            new Object[]{"Sanelite Cinemas: South Bopal", "Gamara Capital, South Bopal", 23.0265, 72.4635},
            new Object[]{"P Square Movieplex, Gota", "Vandemataram Icon, Gota", 23.1035, 72.5465},
            new Object[]{"Rupam Multiplex: Ahmedabad", "Relief Road, Gheekanta", 23.0315, 72.5935},
            new Object[]{"Rupam Arth Cineplex: Sanand", "Station Road, Sanand", 22.9935, 72.3735},
            new Object[]{"Savvy Swaraaj Miniplex: Gota", "Godrej Garden City Road", 23.1115, 72.5535},
            new Object[]{"City Pulse: Orient Miniplex", "Gujarat College, Ellisbridge", 23.0265, 72.5685}
        ));         // 8. Populate Pune Cinemas (With GPS Coordinates)
     // 6. Populate Pune
        seedCinemas("Pune", Arrays.asList(
            // WEST PUNE (Wakad, Hinjawadi, Aundh, Baner)
            new Object[]{"INOX: Megaplex Phoenix Mall of the Millennium", "3rd Floor, Phoenix Mall of the Millennium, Wakad", 18.5955, 73.7635},
            new Object[]{"E-SQUARE: Xion Mall, Hinjawadi", "1st Floor, XION Mall, Hinjawadi Road, Wakad", 18.5995, 73.7555},
            new Object[]{"PVR: Grand Highstreet Mall, Hinjawadi", "Grand Highstreet Mall, 2nd Floor, Hinjawadi", 18.5875, 73.7385},
            new Object[]{"Cinepolis: Nexus WESTEND Mall, Aundh", "Wireless Colony, Aundh, Pune", 18.5615, 73.8075},
            new Object[]{"PVR: Icon, The Pavillion Pune", "Pavillion Mall, 3rd Floor, Senapati Bapat Road", 18.5325, 73.8315},

            // EAST PUNE (Viman Nagar, Kharadi, Hadapsar, Magarpatta)
            new Object[]{"PVR: Phoenix Market City, Pune", "2nd Floor, Phoenix Market City, Nagar Road, Viman Nagar", 18.5625, 73.9165},
            new Object[]{"Cinepolis: Seasons Mall, Pune", "3rd Floor, Seasons Mall, Magarpatta City, Hadapsar", 18.5195, 73.9315},
            new Object[]{"City Pride, Nyati Plaza: Kharadi", "Nyati Plaza, Thite Nagar, Kharadi", 18.5495, 73.9405},
            new Object[]{"MovieMax: Amanora Town Centre", "Amanora Park Town, Hadapsar", 18.5185, 73.9345},
            new Object[]{"Bollywood Multiplex: Kharadi", "Old Mundhwa Road, Sector Number 3, Kharadi", 18.5475, 73.9455},
            new Object[]{"MovieMax Gold: Mariplex Mall, Kalyani Nagar", "Marigold Complex, Kalyani Nagar", 18.5415, 73.9055},
            new Object[]{"Vaibhav Chitramandir: Hadapsar", "Pune Solapur Road, Near Canara Bank, Hadapsar", 18.5035, 73.9265},
            new Object[]{"MovieMax Edition (Luxe): Amanora", "Amanora Town Centre, Amanora Park Town", 18.5185, 73.9348},
            new Object[]{"Cinepolis: VIP Seasons Mall, Pune", "Magarpatta Police Station Road, Hadapsar", 18.5198, 73.9318},

            // CENTRAL PUNE (Shivajinagar, Camp, Deccan, Kothrud)
            new Object[]{"City Pride: Kothrud", "Survey No.20/1,2, Kothrud, Near Tara Residency", 18.5015, 73.8185},
            new Object[]{"PVR: Kumar Pacific, Pune", "4th Floor, Kumar Pacific Mall, Shankar Seth Road", 18.5035, 73.8745},
            new Object[]{"E-SQUARE: University Road", "University Road, Premnagar, Ashok Nagar", 18.5365, 73.8345},
            new Object[]{"Rahul 70 MM: Shivajinagar", "Ganeshkhind Road, Shivaji Nagar", 18.5325, 73.8485},
            new Object[]{"City Pride: Mangala Cinema", "Shivaji Road, Shivaji Nagar", 18.5235, 73.8545},
            new Object[]{"INOX: Bund Garden Road", "Plot No.D, Bund Garden Road, Agarkar Nagar", 18.5335, 73.8795},
            new Object[]{"PVR: Directors Cut, KOPA, Pune", "KOPA Mall, Ghorpadi, Mundhwa Road", 18.5355, 73.8965},
            new Object[]{"City Pride: R Deccan", "R Deccan Mall, Jangli Maharaj Road, Deccan", 18.5185, 73.8415},
            new Object[]{"Victory Theatre: Camp", "Gen Thimayya Road, Camp", 18.5165, 73.8765},
            new Object[]{"CinePRO: Vasant Cinema", "Near Nana Wada, Shivaji Maharaj Road, Budhwar Peth", 18.5135, 73.8565},

            // SOUTH PUNE (Sinhagad Rd, Satara Rd, NIBM)
            new Object[]{"Abhiruchi City Pride: Sinhagad Road", "Near Flyover, Sinhagad Road, Vadgaon Budruk", 18.4575, 73.8215},
            new Object[]{"City Pride: Satara Road", "Market Yard, Pune Satara Road", 18.4855, 73.8575},
            new Object[]{"INOX: Royal Heritage Mall, NIBM", "Royale Heritage Mall, Mohammed Wadi, NIBM Ext", 18.4725, 73.9085},
            new Object[]{"Rajhans Cinemas: 93 Avenue Mall", "Fatima Nagar Junction, Wanoworie", 18.5015, 73.9015},
            new Object[]{"Fun Time Multiplex: Sinhagad Road", "Near Manik Baug Petrol Pump, Sinhagad Road", 18.4735, 73.8265},

            // PIMPRI-CHINCHWAD (PCMC)
            new Object[]{"INOX: Elpro City Square, Chinchwad", "2nd Floor, Elpro City Square Mall, Chinchwad", 18.6365, 73.7965},
            new Object[]{"Vishal Cinemaas: Pimpri", "Old Pune-Mumbai Highway, Pimpri-Chinchwad", 18.6255, 73.8115},
            new Object[]{"Miraj Cinemas: Spine City Mall", "Spine Road, Sant Nagar, Pune", 18.6585, 73.8415},
            new Object[]{"City Pride Royal Cinemas: Rahatani", "Spot-18 Mall, 3rd Floor, Rahatani", 18.5995, 73.7895},
            new Object[]{"INOX: Jai Ganesh, Akurdi", "Jai Ganesh Vision Mall, Akurdi Chowk", 18.6475, 73.7745},
            new Object[]{"Funtime Deluxe: Pimpri", "Deluxe Fortune Mall, Shastri Nagar, Pimpri", 18.6285, 73.8055},
            new Object[]{"Ashok Theatre: Pimpri", "Shastri Nagar, Pimpri Colony", 18.6265, 73.8085},

            // OUTSKIRTS & RURAL (Talegaon, Chakan, etc.)
            new Object[]{"Shri Shivaji Talkies: Talegaon", "Somwar Peth, Talegaon Dabhade", 18.7295, 73.6815},
            new Object[]{"Laxmi Cineplex: Narayangaon", "Telephone Exchange Road, Narayangaon", 19.1135, 73.9785},
            new Object[]{"Dharmatma Cinemark: Chakan", "City Plaza, Talegaon, Chakan Road", 18.7565, 73.8545},
            new Object[]{"Vikas Cinema: Manchar", "Tal: Ambegaon, At Post Manchar", 19.0035, 73.9385},
            new Object[]{"Funsquare Cinema: Ghotawade Phata", "Pirangut, Pune", 18.5065, 73.6935},
            new Object[]{"Vilux Talkies: Khadki", "New Excelsior Complex, Elphinston Road, Khadki", 18.5685, 73.8445},
            new Object[]{"Shevanta Cinema: Junnar", "Delhi Peth, Junnar, Near Old Bus Stop", 19.2065, 73.8785},
            new Object[]{"Chhotu Maharaj Cine Cafe", "Vasuli Phata, Chakan MIDC", 18.7735, 73.7915}
        ));         // 9. Populate Chennai Cinemas (From Screenshots)
         // 9. Populate Chennai Cinemas (With GPS Coordinates)
     // 7. Populate Chennai
        seedCinemas("Chennai", Arrays.asList(
            // CENTRAL CHENNAI (T. Nagar, Royapettah, Egmore, Vadapalani)
            new Object[]{"KC (KrishnaveniCinemas) RG3 LASER", "No 3 Usman Road, T. Nagar, Chennai", 13.0415, 80.2335},
            new Object[]{"AGS Cinemas: T. Nagar", "No: 24/1, GN Chetty Road, T. Nagar", 13.0435, 80.2485},
            new Object[]{"PVR: Sathyam, Royapettah", "8, Thiru Vi Ka Road, Royapettah, Chennai", 13.0535, 80.2585},
            new Object[]{"PVR: Palazzo, The Nexus Vijaya Mall", "3rd Floor, 183, Arcot Road, Vadapalani", 13.0485, 80.2095},
            new Object[]{"INOX: Chennai Citi Centre, Dr. RK Salai", "3rd Floor, Chennai Citi Center, Doctor Radha Krishnan Salai", 13.0425, 80.2675},
            new Object[]{"Woodlands Theatre: Chennai", "25, Westcott Road, Royapettah", 13.0545, 80.2635},
            new Object[]{"INOX National: Arcot Road", "Chandra Metro Mall, Arcot Road, Aranganathan Nagar, Valasaravakkam", 13.0405, 80.1915},
            new Object[]{"Kasi Talkies Dolby Atmos: Ashok Nagar", "No-4, Pillaiyar Koil St, Jafferkhanpet", 13.0315, 80.2115},

            // OMR & ECR (The IT Corridor)
            new Object[]{"AGS Cinemas OMR: Navlur", "Vivira Mall, 5th Floor, Navalur", 12.8615, 80.2265},
            new Object[]{"INOX: The Marina Mall, OMR", "3rd Floor, The Marina Mall, Old Mahabalipuram Road, Egatoor", 12.8255, 80.2215},
            new Object[]{"The Vijay Park Multiplex: Injambakkam ECR", "No.56, Anna Enclave, Injambakkam, Chennai", 12.9185, 80.2545},
            new Object[]{"MAYAJAAL Multiplex: ECR, Chennai", "No. 34/1, East Coast Road, Kanathur", 12.8535, 80.2385},
            new Object[]{"PVR: Heritage RSL ECR, Chennai", "1st Floor, Near Uthandi Toll Gate, Uthandi Village", 12.8855, 80.2435},
            new Object[]{"Cinepolis: BSR Mall, OMR, Thoraipakkam", "BSR Mall, Thoraipakkam, Seevaram", 12.9565, 80.2415},
            new Object[]{"GanapathyRam Theatre 4K Dolby 7.1", "101 Lattice Bridge Road, Adyar", 13.0015, 80.2565},

            // SOUTH CHENNAI (Velachery, Tambaram, Pallavaram)
            new Object[]{"INOX: LUXE Phoenix Market City, Velachery", "2nd Floor, Phoenix Market City, Velachery Main Road", 12.9915, 80.2175},
            new Object[]{"PVR: Grand Mall, Velachery", "3rd Floor, Grand Square Mall, Velachery Road", 12.9865, 80.2215},
            new Object[]{"PVR: Grand Galada, Pallavaram", "Grand Galada Mall, Kohinoor-2, Officers Line", 12.9695, 80.1465},
            new Object[]{"Janatha Theatre 4K AC DTS (JBL AUDIO)", "Srinivasa Perumal Koil, Rajendra Nagar, Pallavaram", 12.9635, 80.1515},
            new Object[]{"National Theatre 4K Dolby Atmos: Tambaram", "Rajaji Road, West Tambaram", 12.9235, 80.1135},
            new Object[]{"Varadharaja Theatre: Chitlapakkam", "Chitlapakkam Main Road, Nehru Nagar", 12.9365, 80.1415},
            new Object[]{"Medavakkam Kumaran Cinemas RGB LASER", "No. 4/347 Velachery Main Road, Medavakkam", 12.9165, 80.1915},
            new Object[]{"Kumaran Theatre PROVA 4K DOLBY ATMOS", "No 364, Mount - Madipakkam Road, Ullagaram", 12.9735, 80.1965},
            new Object[]{"Vetrivel RGB DOLBY: Nanganallur", "18, M.G.R. Road, Telegraph Colony, Nanganallur", 12.9815, 80.1835},
            new Object[]{"Jothi Theatre 4K A/c DTS: ST Thomas Mount", "Alandur - Parangimalai, Parade Road", 13.0015, 80.1985},

            // NORTH & WEST CHENNAI (Anna Nagar, Koyambedu, Avadi)
            new Object[]{"PVR: VR Chennai, Anna Nagar", "3rd Floor, VR Mall, Metro Zone, No 44, Pillaiyar Koil Street", 13.0815, 80.1915},
            new Object[]{"PVR: Ampa Mall, Nelson Manickam Road", "4th Floor, Ampa Skywalk Mall, Aminjikarai", 13.0735, 80.2215},
            new Object[]{"Rohini Silver Screens: Koyambedu", "141/2, Poonamallee High Road, Koyembedu", 13.0715, 80.1935},
            new Object[]{"AGS Cinemas: Villivakkam", "No. 1/1, Mettu Street Villivakkam", 13.1085, 80.2085},
            new Object[]{"AGS Cinemas: Maduravoyal", "No 3/47, Alapakkam Main Road, Subramaniapuram", 13.0485, 80.1615},
            new Object[]{"PVR: Aerohub, Chennai", "Aerohub East Wing, MLCP Block, Chennai International Airport", 12.9815, 80.1635},
            new Object[]{"Green Cinemas 4K Atmos, PLF: Padi", "64, Periyar Nagar, Mes Colony, Chennai", 13.1065, 80.1835},
            new Object[]{"Murugan Cinemas 4K: Ambattur", "Ambattur, Chennai", 13.1115, 80.1535},
            new Object[]{"Remy Cinemas A/C DTS 2K 3D Laser: Avadi", "2nd Main Road, JB Estate, Chelliamman Kovil Street", 13.1085, 80.1015},
            new Object[]{"Rakki Cinemas: Ambattur", "Ambattur, Chennai", 13.1145, 80.1485},
            new Object[]{"Vela Cinemas RGB 4KLaser DolbyAtmos", "National Highway 205, Thiruninravur", 13.1185, 80.0385},
            new Object[]{"Gokulam Cinemas 4K Dolby Atmos: Poonamallee", "795C, Old Sundar Theatre Complex, Rukmani Nagar", 13.0515, 80.0935},
            new Object[]{"Vigneshwara Theatre RGB Laser: Poonamallee", "MG Nagar, Trunk Road, Opposite to Poonamallee fort", 13.0485, 80.0915}
        ));         // 10. Populate Kolkata Cinemas (With GPS Coordinates)
     // 8. Populate Kolkata
        seedCinemas("Kolkata", Arrays.asList(
            // SOUTH & CENTRAL KOLKATA (The Heart of the City)
            new Object[]{"INOX: South City, Kolkata", "South City Mall, Prince Anwar Shah Road", 22.5015, 88.3615},
            new Object[]{"Nandan: Kolkata", "1/1, A.J.C. Bose Road, Near West Bengal Bangla Academy", 22.5425, 88.3445},
            new Object[]{"Cinepolis: Lake Mall, Kolkata", "Lake Mall, Rash Behari Avenue", 22.5165, 88.3515},
            new Object[]{"INOX: Quest Mall", "Quest Mall, Syed Amir Ali Avenue, Park Circus", 22.5395, 88.3655},
            new Object[]{"Cinepolis: Acropolis Mall, Kolkata", "Acropolis Mall, Rajdanga Main Road, Kasba", 22.5135, 88.3965},
            new Object[]{"Navina Cinema: Tollygunge", "85, Prince Anwar Shah Road, Tollygunge", 22.5045, 88.3485},
            new Object[]{"INOX: Forum Mall, Elgin Road", "10/3, Elgin Road, Bhawanipur", 22.5365, 88.3525},
            new Object[]{"Priya Cinema: Rashbehari Avenue", "95, Rash Behari Avenue, Manoharpukur, Kalighat", 22.5185, 88.3545},
            new Object[]{"INOX: Metro, Jawaharlal Nehru Road", "Metro Cinema, Jawaharlal Nehru Road, Esplanade", 22.5645, 88.3525},
            new Object[]{"Menoka Cinema: Kolkata", "Sarat Chatterjee Avenue, Sarat Bose Road, Kalighat", 22.5125, 88.3565},
            new Object[]{"Basusree Cinema: Kolkata", "102, S. P. Mukherjee Road, Kalighat", 22.5235, 88.3485},
            new Object[]{"New Empire Cinema: Kolkata", "Humayun Place, New Market Area, Dharmatala", 22.5625, 88.3535},
            new Object[]{"SSR Globe Cinemas: New Market", "Globe Mall, Lindsay Street, Dharmatala", 22.5615, 88.3545},
            new Object[]{"Hind INOX: Kolkata", "Ganesh Chandra Avenue, Bow Bazaar", 22.5655, 88.3585},
            new Object[]{"Prachi Cinema: Kolkata", "124/A, A.J.C Bose Road, Sealdah", 22.5565, 88.3715},
            new Object[]{"Minar Cinema: Kolkata", "Bidhan Sarani, Shyam Bazar, Sovabazar", 22.5925, 88.3725},
            new Object[]{"Bijoli Cinema: Kolkata", "Shyama Prasad Mukherjee Road, Bhawanipur", 22.5285, 88.3475},
            new Object[]{"Binodini Theatre (Star Theatre)", "Bidhan Sarani, Beadon Street, Sovabazar", 22.5935, 88.3695},

            // SALT LAKE & NEW TOWN (IT Hubs)
            new Object[]{"INOX: City Centre II, Rajarhat", "City Center 2 Mall, New Town, Rajarhat", 22.6245, 88.4635},
            new Object[]{"RDB Cinemas: Salt Lake", "Block EP-GP, Sector 5, Salt Lake", 22.5715, 88.4325},
            new Object[]{"INOX: City Center, Salt Lake", "DC Block I, Sector-1, City Center Mall", 22.5885, 88.4085},
            new Object[]{"Miraj Cinemas: The Terminus, New Town", "The Terminus Building, BG/12, AA-1B, New Town", 22.5805, 88.4685},
            new Object[]{"Bioscope: Axis Mall, Rajarhat", "Axis Mall, CF Block, New Town", 22.5795, 88.4625},
            new Object[]{"Nazrultirtha Cinema: Kolkata", "Service Road, New Town, Rajarhat", 22.5755, 88.4615},
            new Object[]{"PVR: Mani Square Mall, Kolkata", "Mani Square Mall, EM Bypass Road", 22.5765, 88.4025},
            new Object[]{"PVR: Uniworld Downtown Mall, New Town", "Uniworld City, Action Area III, New Town", 22.5485, 88.4985},
            new Object[]{"Miraj Cinemas: Downtown Mall, Salt Lake", "IB Block, Sector 3, Salt Lake", 22.5685, 88.4115},
            new Object[]{"INOX: Swabhumi, Maulana Azad Sarani", "89C, Moulana Abul Kalam Azad Sarani", 22.5695, 88.3985},

            // NORTH KOLKATA & SUBURBS (Barasat, Barrackpore, etc.)
            new Object[]{"PVR: Diamond Plaza, Jessore Kolkata", "Diamond City Mall, Jessore Road, Satgachi", 22.6135, 88.4045},
            new Object[]{"INOX: Star Mall, Madhyamgram", "Star Mall, Jessore Road, Madhyamgram", 22.6985, 88.4525},
            new Object[]{"Atindra Cinema: Barrackpore", "Ghosh Para Road, Near Barrackpore Railway Station", 22.7635, 88.3715},
            new Object[]{"SSR Cinemas, Suncity Mall: Barasat", "Suncity Mall, Jessore Road, Champadali", 22.7215, 88.4845},
            new Object[]{"Jaya Cinemas: City Mall, Barasat", "Jessore Road, Barasat, Near Duck Banglow", 22.7185, 88.4815},
            new Object[]{"Rathindra Multiplex: Sodepur", "Rathindra Cinema Hall, Deshabondhu Nagar", 22.6935, 88.3845},
            new Object[]{"Sonali Cinema: Dunlop", "Barrackpore Trunk Road, Narendra Nagar", 22.6515, 88.3745},
            new Object[]{"Rupmandir Cinema: Belghoria", "Beehive Garden, Near Feeder Road", 22.6635, 88.3825},
            new Object[]{"Jayanti Cinema: Barrackpore", "B.T Road, Barrackpore Chiriamore", 22.7515, 88.3685},
            new Object[]{"Amala Cinema: Barrackpore", "Barrackpore - Barasat Road", 22.7565, 88.3725},
            new Object[]{"Lali Cinema: Barasat", "Krishnanagar Road, Barasat", 22.7245, 88.4865},
            new Object[]{"Padma Cinema: Kolkata", "Barasat Road, Sodepur", 22.6965, 88.3885},

            // HOWRAH & HOOGHLY SIDE
            new Object[]{"PVR: Avani, Kolkata", "Avani River Side Mall, Jagat Benarjee Ghat Road, Howrah", 22.5615, 88.3245},
            new Object[]{"INOX: Forum Rangoli Mall, Belur", "Girish Ghosh Road, Belur, Howrah", 22.6315, 88.3545},
            new Object[]{"SVF Cinemas: Platina Mall, Howrah", "Platina Mall, Nityadhan Mukherjee Road", 22.5845, 88.3315},
            new Object[]{"Miraj Cinemas: Aurbindo Mall, Howrah", "Aurobino Mall, Sri Arabinda Road, Salkia", 22.5985, 88.3445},

            // SOUTH 24 PARGANAS & OUTSKIRTS (Behala, Baruipur, etc.)
            new Object[]{"INOX: Hiland Park", "Metropolis Mall, E.M. Bypass, Chak Garia", 22.4815, 88.3965},
            new Object[]{"Asoka Cinema: Behala", "Diamond Harbour Road, Arcadia, Behala", 22.4935, 88.3185},
            new Object[]{"SSR Ajanta Cinema: Behala", "Diamond Harbour Road, Pathak Para, Behala", 22.4985, 88.3145},
            new Object[]{"SVF Cinemas: Wood Square Mall", "Wood Square Mall, Narendrapur", 22.4385, 88.3945},
            new Object[]{"SVF Cinemas: Baruipur Show House", "Puratan Bazaar, Baruipur", 22.3585, 88.4315},
            new Object[]{"Radha Studio: Tollygunge", "Deshpran Sasmal Road, Tollygunge", 22.5015, 88.3445},
            new Object[]{"SSR Cinemas: Maheshtala", "Purti Plaza, Budge Budge Trunk Road", 22.5085, 88.2715},
            new Object[]{"Utpal Dutta Mancha: Maheshtala", "Biren Roy Road W, Badamtala", 22.4935, 88.2945},
            new Object[]{"Elora Multiplex: Champahati", "Baruipur - Champahati Road", 22.4315, 88.4815},
            new Object[]{"Lila Cinema: Baruipur", "Baruipur - Champahati - Ghatakpukur Road", 22.3615, 88.4345},
            new Object[]{"Uma Talkies: Bakhrahat", "Roypur Road, Bakhrahat", 22.4115, 88.2345},
            new Object[]{"Sobha Talkies: Betberia", "Near Belegachi, Post Office, Betberia", 22.3815, 88.5145}
        ));         // 11. Populate Kochi Cinemas (With GPS Coordinates)
     // 11. Populate Kochi
        seedCinemas("Kochi", Arrays.asList(
            // CITY CENTER (MG Road, Marine Drive, Ernakulam)
            new Object[]{"Cinepolis: Centre Square, Kochi", "6th floor, Centre Square Mall, M.G.Road, Ernakulam", 9.9795, 76.2815},
            new Object[]{"Cinepolis: VIP Centre Square Mall, Kochi", "6th floor, Centre Square Mall, M.G.Road, Ernakulam", 9.9795, 76.2815},
            new Object[]{"Shenoys: Kochi", "Shenoys Vistarama, M G Road, Cochin", 9.9735, 76.2835},
            new Object[]{"Padma Cinema: Kochi", "Shenoy Cinemax, Level 1, MG Road, Kacheripady", 9.9755, 76.2825},
            new Object[]{"Savita Cinema: Kochi", "41/32, Alberts College Road, Ernakulam", 9.9825, 76.2795},
            new Object[]{"Kavitha Theatre 4K 3D Dolby 7.1: Ernakulam", "Paza Hotel, Mahatma Gandhi Road, Shenoys", 9.9715, 76.2845},
            new Object[]{"Sridar: Marine Drive, Kochi", "Marine Drive, Shanmugham Road, Opposite Canara Bank", 9.9785, 76.2765},

            // EDAPPALLY, BYPASS & MARADU
            new Object[]{"PVR: Lulu, Kochi", "Lulu International Shopping Mall, Edappally, Nethaji Nagar", 10.0275, 76.3078},
            new Object[]{"PVR: Oberon Mall, Kochi", "NH Bypass, Edapally Junction, Ernakulam", 10.0205, 76.3155},
            new Object[]{"PVR: Forum Mall, Kochi", "3rd Floor, Forum Mall, NH-47, Vytilla Aroor Bypass", 9.9505, 76.3235},
            new Object[]{"Vanitha Cineplex RGB Laser 4K 3D ATMOS: Edappally", "Edappally Toll Junction, Nethaji Nagar", 10.0245, 76.3075},
            new Object[]{"PAN Cinemas Nucleus Mall 4K ATMOS", "Nucleus Mall, Maradu, Kochi-Madurai-Tondi Point Hwy", 9.9435, 76.3185},

            // ALUVA, ANGAMALY & PERUMBAVOOR
            new Object[]{"Matha Madhurya RGB Laser 3D Dolby Atmos: Aluva", "Periyar Nagar, T2/10, Perumbavoor Aluva Road", 10.1115, 76.3535},
            new Object[]{"MY Cinemas, KSRT Complex, Angamaly", "5th Floor, KSRTC Complex, Angamaly", 10.1965, 76.3865},
            new Object[]{"JM Movies Josh Mall: Mookkannoor Angamaly", "Mookkannoor, Ezhattumugham Road, Josh Mall", 10.2315, 76.4185},
            new Object[]{"MY Cinemas RedCarpet: Kariyad", "National Highway 47, Nedumbassery, Near Cochin Airport", 10.1685, 76.3765},
            new Object[]{"EVM Cinema A/C 4K RGB Laser 3D: Perumbavoor", "Old Kaala Vayal, Perumbavoor, Kochi", 10.1145, 76.4785},
            new Object[]{"Aashirvad Cineplexx: Perumbavoor", "K.S.R.T.C Bus Stand Road, Perumbavoor", 10.1165, 76.4765},
            new Object[]{"Casino Talkies A/C Real Laser 3D DOLBY 7.1: Aluva", "Little Flower Ln, Periyar Nagar, Aluva", 10.1125, 76.3515},
            new Object[]{"Four Star Movies: Manjapra, Angamaly", "Canal Pass, Manjapra, Angamaly", 10.2185, 76.4465},
            new Object[]{"Zeenath Theatre 2K A/C: Aluva", "Aluva, Periyar Nagar, Sub Jail Road", 10.1085, 76.3495},

            // NORTH PARAVUR, VYPIN & FORT KOCHI
            new Object[]{"Shafaz Laser & Grand 4K Theatre North Paravoor", "Chendamangalam Junction, North Paravur", 10.1465, 76.2315},
            new Object[]{"Kairali Sree Theater: North Parvur", "North Paravur, Pullomkulam", 10.1515, 76.2285},
            new Object[]{"G Cinemas Fort Kochi: 4K Dolby ATMOS", "Pandikudy, Anavathil, Thamaraparambu", 9.9565, 76.2485},
            new Object[]{"Majestic Multiplex 4K RGB Laser: Narakkal", "Vypin - Pallipuram Road, Narakkal", 10.0415, 76.2165},
            new Object[]{"K Cinemas 4K Dolby Atmos Tripplebeam 3D: Cherai", "Ayyampilly, Pallippuram Rd, Kochi", 10.1385, 76.1985},

            // TRIPUNITHURA & OTHERS
            new Object[]{"New Central Talkies RGB Laser 4K 3D Dolby Atmos", "New Central Talkies, Vaikom Road, Tripunithura", 9.9465, 76.3515},
            new Object[]{"Central Talkies RGB Laser 4K 3D Dolby Atmos", "Central Talkies, Near Old Bus Stand, Tripunithura", 9.9485, 76.3525},
            new Object[]{"M Cinemas 4K 3D Dolby ATMOS: Varapuzha", "Chettibhagam Main Rd, Varapuzha", 10.0765, 76.2715},
            new Object[]{"J MAX 2K 3D Dolby 7.1: PATTIMATTOM", "Pattimattom PO, Pattimattom, Kerala", 10.0385, 76.4865}
        ));    
    }

 // --- HELPER METHODS ---

    private void createCitySafe(String name, String icon) {
        if (cityRepo.findByName(name) == null) {
            City c = new City();
            c.setName(name);
            c.setIconUrl(icon);
            cityRepo.save(c);
            System.out.println("   + Created City: " + name);
        }
    }

    private void seedCinemas(String cityName, List<Object[]> cinemasData) {
        City city = cityRepo.findByName(cityName);
        if (city == null) {
            System.out.println("⚠️ Skipping " + cityName + " (City not found)");
            return;
        }

        for (Object[] data : cinemasData) {
            String name = (String) data[0];
            String address = (String) data[1];
            Double lat = (Double) data[2];
            Double lng = (Double) data[3];

            // Only create if it doesn't exist to prevent duplicates
            if (cinemaRepo.countByName(name) == 0) {
                Cinema c = new Cinema();
                c.setName(name);
                c.setAddress(address);
                c.setLatitude(lat);
                c.setLongitude(lng);
                c.setType("Multiplex");
                c.setCity(city);
                cinemaRepo.save(c);
            }
        }
        System.out.println("✅ Loaded " + cinemasData.size() + " Cinemas for " + cityName);
    }
}