//
// Created by Adittya on 7/6/2022.
//

#ifndef PLUGIN_RANDOMCOLOR_H
#define PLUGIN_RANDOMCOLOR_H

#include "../SocketInformations/WordStructure.h"

#ifdef RANDOMCOLOR_THREAD_SAFE
#include <mutex>
#endif
using namespace std;
class RandomColor
        : public Color {
public:
    enum Color
    {
        Red,
        RedOrange,
        Orange,
        OrangeYellow,
        Yellow,
        YellowGreen,
        Green,
        GreenCyan,
        Cyan,
        CyanBlue,
        Blue,
        BlueMagenta,
        Magenta,
        MagentaPink,
        Pink,
        PinkRed,
        RandomHue,
        BlackAndWhite,
        Brown
    };

    enum Luminosity
    {
        Dark,
        Normal,
        Light,
        Bright,
        RandomLuminosity
    };

    typedef std::initializer_list<Color> ColorList;


    static const ColorList AnyRed;
    static const ColorList AnyOrange;
    static const ColorList AnyYellow;
    static const ColorList AnyGreen;
    static const ColorList AnyBlue;
    static const ColorList AnyMagenta;
    static const ColorList AnyPink;

    struct Range
    {
        Range( int value = 0 );
        Range( int left, int right );
        int& operator []( int i );
        int operator []( int i ) const;
        int size() const;

        int values[2];
    };


    int generate( Color color = RandomHue, Luminosity luminosity = Normal );

    int generate( ColorList colors, Luminosity luminosity = Normal );

    int generate( const Range& hueRange, Luminosity luminosity = Normal );


    void setSeed( int seed );

private:
    struct SBRange
    {
        SBRange( int s = 0, int bMin = 0, int bMax = 100 );

        int s;
        int bMin;
        int bMax;
    };

    struct ColorInfo
    {
        Color color;
        Range hRange;
        std::vector<SBRange> ;
    };

    int generate( int h, const ColorInfo&, Luminosity );
    int pickSaturation( const ColorInfo&, Luminosity );
    int pickBrightness( int s, const ColorInfo&, Luminosity );

    Range getBrightnessRange( int s, const ColorInfo& ) const;
    const ColorInfo& getColorInfo( int h ) const;
    int randomWithin( const Range& );
    int HSBtoRGB( double h, double s, double b ) const;

    static const std::vector<ColorInfo> colorMap;
#ifdef RANDOMCOLOR_THREAD_SAFE
    std::mutex mutex;
#endif
};

inline RandomColor::Range::Range( int left, int right )
        : values{left, right}
{}

inline RandomColor::Range::Range( int value )
        : values{value, value}
{}

inline int& RandomColor::Range::operator []( int i )
{
    return values[i];
}

inline int RandomColor::Range::operator []( int i ) const
{
    return values[i];
}

inline int RandomColor::Range::size() const
{
    return values[1] - values[0];
}
#endif //PLUGIN_RANDOMCOLOR_H
