//
// Created by Adittya on 6/16/2022.
//

namespace ay
{
    template <std::size_t N, char KEY>
    class obfuscator
    {
    public:
        constexpr obfuscator(const char* data)
        {
            static_assert(KEY != '\0', "KEY must not be the null character.");

            for (std::size_t i = 0; i < N; i++)
            {
                m_data[i] = data[i] ^ KEY;
            }
        }

        constexpr const char* getData() const
        {
            return &m_data[0];
        }

        constexpr std::size_t getSize() const
        {
            return N;
        }

        constexpr char getKey() const
        {
            return KEY;
        }

    private:

        char m_data[N]{};
    };

    template <std::size_t N, char KEY>
    class OBFUSCATE_data
    {
    public:
        OBFUSCATE_data(const obfuscator<N, KEY>& obfuscator)
        {
            for (std::size_t i = 0; i < N; i++)
            {
                m_data[i] = obfuscator.getData()[i];
            }
        }

        ~OBFUSCATE_data()
        {
            for (std::size_t i = 0; i < N; i++)
            {
                m_data[i] = 0;
            }
        }

        operator char*()
        {
            decrypt();
            return m_data;
        }
        operator std::string()
        {
            decrypt();
            return m_data;
        }

        void decrypt()
        {
            if (is_encrypted())
            {
                for (std::size_t i = 0; i < N; i++)
                {
                    m_data[i] ^= KEY;
                }
            }
        }


        void encrypt()
        {
            if (!is_encrypted())
            {
                for (std::size_t i = 0; i < N; i++)
                {
                    m_data[i] ^= KEY;
                }
            }
        }


        bool is_encrypted() const
        {
            return m_data[N - 1] != '\0';
        }

    private:

        char m_data[N];
    };

    template <std::size_t N, char KEY = '.'>
    constexpr auto make_obfuscator(const char(&data)[N])
    {
        return obfuscator<N, KEY>(data);
    }
}

// Obfuscates the string 'data' at compile-time and returns a reference to a
// ay::OBFUSCATE_data object with global lifetime that has functions for
// decrypting the string and is also implicitly convertable to a char*
#define OBFUSCATE(data) OBFUSCATE_KEY(data, '.')

// Obfuscates the string 'data' with 'key' at compile-time and returns a
// reference to a ay::OBFUSCATE_data object with global lifetime that has
// functions for decrypting the string and is also implicitly convertable to a
// char*
#define OBFUSCATE_KEY(data, key) \
	[]() -> ay::OBFUSCATE_data<sizeof(data)/sizeof(data[0]), key>& { \
		constexpr auto n = sizeof(data)/sizeof(data[0]); \
		static_assert(data[n - 1] == '\0', "String must be null terminated"); \
		constexpr auto obfuscator = ay::make_obfuscator<n, key>(data); \
		static auto OBFUSCATE_data = ay::OBFUSCATE_data<n, key>(obfuscator); \
		return OBFUSCATE_data; \
	}()
